package com.myccnice.practice.manual.jdk.java.util.concurrent.locks;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;

import sun.misc.Unsafe;

public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {

    private static final long serialVersionUID = 7373984972572414691L;

    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    private static final long nextOffset;

    static {
        try {
            // 获取相关属性的地址偏移量
            stateOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset(Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset(Node.class.getDeclaredField("next"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * 设置头结点，如果当前头节点为空
     */
    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    /**
     * CAS更新尾节点
     */
    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    /**
     * CAS更新当前节点的等待状态
     */
    private static final boolean compareAndSetWaitStatus(Node node, int expect, int update) {
        return unsafe.compareAndSwapInt(node, waitStatusOffset, expect, update);
    }

    /**
     * CAS操作更新节点的后继节点
     */
    private static final boolean compareAndSetNext(Node node, Node expect, Node update) {
        return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
    }

    protected AbstractQueuedSynchronizer() {

    }

    /**
     * 头结点，不存储Thread，仅保存next结点的引用。
     * 当持有锁的线程释放锁以后，如果是等待队列获取到了加锁权限，
     * 则会在等待队列头部取出第一个线程去获取锁，获取锁的线程会被移出队列；
     */
    private transient volatile Node head;
    /**
     * 尾节点
     */
    private transient volatile Node tail;
    /**
     * 状态变量：该变量对不同的子类实现具有不同的意义。
     * 对ReentrantLock来说，每次加锁都会将state的值+1，state等于几，就代表当前持有锁的线程加了几次锁;
     *  无锁时state=0，有锁时state>0,可重入的条件是判断加锁线程就是当前持有锁的线程时
     *  （即exclusiveOwnerThread==Thread.currentThread()）即可加锁，
     *  解锁时每解一次锁就会将state减1，state减到0后，锁就被释放掉，这时其它线程可以加锁；
     */
    private volatile int state;

    protected final int getState() {
        return state;
    }

    protected final void setState(int newState) {
        state = newState;
    }

    /**
     * CAS操作更新锁的状态
     */
    protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    /**
     * 等待节点入队
     */
    private Node enq(final Node node) {
        // 经典的CAS自旋，目的是将节点加入队列尾部
        for (;;) {
            Node t = tail;
            if (t == null) {
                // 从这里看出头节点时不包含线程的
                // >>> 那为什么不在声明的时候就直接new一个空节点啦？
                if (compareAndSetHead(new Node())) {
                    tail = head;
                }
            } else {
                // 这个操作和快速入队一样，都是简单的链式拼接，只是中间加了CAS判断
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }

    /**
     * 将当前线程包装成一个Node加入等待队列。这时节点的等待状态为0
     */
    private Node addWaiter(Node mode) {
        // 将当前线程包装为一个等待的节点
        Node node = new Node(Thread.currentThread(), mode);
        Node pred = tail;
        // 在队列不为空、竞争不激烈的情况下快速入队
        // >>> 其实这个IF代码块不要也是可以的，但JDK中好多这种对大概率事件的特殊处理。
        if (pred != null) {
            // 将当前节点的前继节点设置为原来的尾节点
            node.prev = pred;
            // CAS更新尾节点为当前节点
            if (compareAndSetTail(pred, node)) {
                // 如果CAS操作成功，将原来尾节点的后继设置为当前节点，入队完成
                pred.next = node;
                return node;
            }
        }
        // 如果快速入库失败，需要自旋入队
        enq(node);
        return node;
    }

    private void setHead(Node node) {
        head = node;
        node.thread = null;
        node.prev = null;
    }

    /**
     * unpark和park类似于wait和notify
     */
    private void unparkSuccessor(Node node) {
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);
        // 选择唤醒的对象是当前节点的后继节点
        Node s = node.next;
        // 如果后继节点为空，或者后继节点状态为取消的时候，需要找到一个有效的节点连唤醒
        if (s == null || s.waitStatus > 0) {
            s = null;
            // >>> 这里非常有意思，我看网上好多人问为啥lea老爷子是从tail开始向前遍历，而不是从前往后遍历
            // >>> 这个具体的理由我也说不上来，但是根据s.waitStatus > 0，应该是跟取消操作有关
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);
    }

    /**
     * 唤醒共享节点
     */
    private void doReleaseShared() {
        // 这里也是和独占模式不同的地方，独占模式下在尝试释放资源成功后，直接去唤醒下一个就OK了，因为独占时永远只有一个线程获取到资源
        // 但是共享模式不同，共享模式会同时有多个线程都可能操作释放，所以必须自旋的方式来唤醒，并且必须判断compareAndSetWaitStatus(h, Node.SIGNAL, 0)
        // 而且必须是在h == head的情况下才能break。因为你拿到的head可能已经不是head了，已经被其他线程唤醒了。
        for (;;) {
            // FIFO队列，都是从头开始遍历
            Node h = head;
            if (h != null && h != tail) {
                int ws = h.waitStatus;
                if (ws == Node.SIGNAL) {
                    if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                        continue;
                    unparkSuccessor(h);
                }
                else if (ws == 0 &&
                        !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                    continue;
            }
            if (h == head)
                break;
        }
    }

    private void setHeadAndPropagate(Node node, int propagate) {
        Node h = head;
        setHead(node);
        // 这里主要是propagate > 0就去唤醒后继节点
        // >>> 但是为什么这些条件是或者h.waitStatus < 0，意思没有多余的资源即是propagate==0也会去唤醒。
        if (propagate > 0 || h == null || h.waitStatus < 0 ||
                (h = head) == null || h.waitStatus < 0) {
            Node s = node.next;
            if (s == null || s.isShared())
                doReleaseShared();
        }
    }

    /**
     * 取消当前节点
     */
    private void cancelAcquire(Node node) {
        if (node == null)
            return;
        // 将当前节点的线程置空
        node.thread = null;
        // 下面这也是将前面取消的节点移除队列
        Node pred = node.prev;
        while (pred.waitStatus > 0)
            node.prev = pred = pred.prev;

        Node predNext = pred.next;
        // 设置当前节点状态为取消
        node.waitStatus = Node.CANCELLED;
        // 如果当前节点为尾节点，就将其前继节点设置为尾节点
        if (node == tail && compareAndSetTail(node, pred)) {
            compareAndSetNext(pred, predNext, null);
        } else {
            int ws;
            // 如果当前节点有前继节点，不是头节点也没有取消时直接将当前节点从等待队列中移除
            // 这个IF就是判断其前继节点是否有效(不是头节点并且不是取消状态)
            // 如果前面的节点都无效的话，需要做一个唤醒操作,即unparkSuccessor。
            // >>> 这个非常有意思，我一开始是没想明白为啥要有这个IF ELSE的判断，如果是我写可能就是直接将当前节点从等待队列中移除，
            // >>> 但是如果这样写有个问题，因为从前面我们知道这个队列后继节点是Park住的处于waiting状态，需要前继节点获取到资源后给后继发信号，
            // >>> 如果当前节点就是最前面那个，直接将其移除，那后面的节点就会一直傻等，再也没法唤醒了。所以如果前面的节点都无效就需要手动unparkSuccessor
            if (pred != head &&
                    ((ws = pred.waitStatus) == Node.SIGNAL ||
                    (ws <= 0 && compareAndSetWaitStatus(pred, ws, Node.SIGNAL))) &&
                    pred.thread != null) {
                Node next = node.next;
                if (next != null && next.waitStatus <= 0)
                    compareAndSetNext(pred, predNext, next);
            } else {
                // 唤醒后继节点，这个在释放资源（release）会讲到
                unparkSuccessor(node);
            }
            node.next = node;
        }
    }

    /**
     * 没有获取到共享资源，需要Park
     * @param pred 前继节点
     * @param node 当前接口
     * @return
     */
    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        // 获取前继节点的状态
        // >>> 节点的几种状态应该要熟记
        int ws = pred.waitStatus;
        // 如果前继节点状态为SIGNAL，意思就是我排在你前面都还没排上号呢，你(当前节点)先去休息（Park）吧，我好了会给你发信号的（SIGNAL：信号）
        if (ws == Node.SIGNAL) {
            return true;
        }
        // 如果前继节点状态>0,我们知道只有1中大于0的状态就是CANCELLED(取消状态)
        // >>> 这个和排队叫号很像，如果前面的人叫了号又不要了。那我肯定会往前排嘛
        // >>> 对于链式操作来说就是:pred = pred.prev; node.prev = pred
        if (ws > 0) {
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            // 最后将next和node连接一下
            pred.next = node;
        } else {
            // 正常情况下，前继节点的waitStatus应该是0，会进入这个分支
            // CAS操作设置前继节点waitStatus为SIGNAL
            // >>> 意思是，告诉前面的哥们，我要休息了(Park)一会到你了你通知我下，这才有了前面的（if (ws == Node.SIGNAL)）
            // >>> 但是这个为什么不直接返回CAS的操作结果，而是返回false。这个我还是没理解到，或者说可以直接返回CAS的操作结果，只是Doug Lea没这么写
            // >>> 我们知道这是在一个自旋操作中，如果返回false的话，还是会再次走到这个判断，这时if (ws == Node.SIGNAL)就成立了，也是返回true，只是多走了一圈
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        return false;
    }

    static void selfInterrupt() {
        Thread.currentThread().interrupt();
    }

    /**
     * 将当前线程Park住，并检查线程中断状态。
     * 线程被Park后会进入waiting状态
     * @return 是否中断
     */
    private final boolean parkAndCheckInterrupt() {
        // park()会让当前线程进入waiting状态。在此状态下，有两种途径可以唤醒该线程：1）被unpark()；2）被interrupt()。
        LockSupport.park(this);
        // 检查线程是否被中断过，并且会清除中断标志
        return Thread.interrupted();
    }

    /**
     * 排队获取资源
     */
    final boolean acquireQueued(final Node node, int arg) {
        // 默认获取资源失败
        boolean failed = true;
        try {
            // 默认线程是不会被打断的
            boolean interrupted = false;
            // 又是一个自旋
            // >>> 为啥大神都喜欢这种方式，而不是while(true)啦
            for (;;) {
                // 获取当前节点的前继节点
                final Node p = node.predecessor();
                // 如果前继是头节点，那说明队伍已经拍到自己了，可以尝试去获取共享资源了
                if (p == head && tryAcquire(arg)) {
                    // 如果资源获取成功，需要把当前节点从队列中移出
                    setHead(node);
                    // 逻辑上没有作用，就是为了help GC
                    // >>> 这个能不能help到GC，根据现在的JVM来看应该已经没有必要了吧
                    p.next = null; // help GC
                    failed = false;
                    // 响应中断
                    // >>> 之前有点二了，想着这前面的代码没有设置中断的地方啊(脑壳打铁)，
                    // >>> 因为这是个自旋操作，他前面的循环(后面那个interrupted = true)可能是设置了中断的
                    return interrupted;
                }
                // 这里shouldParkAfterFailedAcquire的作用有两个
                // 1、将已经取消的线程从队列中清除；2、给前面的节点通通气，即将前面的节点waitStatus设置为SIGNAL
                // 这个操作有2中返回值true/false。true的情况没得说，就是继续走parkAndCheckInterrupt
                // false的话会继续循环，最终要么返回true，要么获取到共享资源。
                // 如果返回true，线程继续走parkAndCheckInterrupt，这时线程会被Park住，进入waiting状态
                // >>> 我理解的这和有锁的同步代码块不一样的地方主要是在线程的状态，synchronized同步块，线程拿不到锁的时候是blocked状态，而这里是waiting状态
                if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) {
                    interrupted = true;
                }
            }
        } finally {
            // 我们从代码中看出正常的返回failed肯定都是false，那么什么时候failed会为true
            // >>> 我的理解是tryAcquire抛出了异常的情况
            if (failed) {
                // 如果失败了需要取消当前节点的资格
                cancelAcquire(node);
            }
        }
    }

    /**
     * 共享模式排队获取资源。注意和独占模式的区别。
     */
    private void doAcquireShared(int arg) {
        // 将当前节点加入队列尾部
        final Node node = addWaiter(Node.SHARED);
        // 获取失败标志
        boolean failed = true;
        try {
            // 中断标志
            boolean interrupted = false;
            for (;;) {
                final Node p = node.predecessor();
                // 和独占模式一样，排队到自己(前继==head)才获取资源
                if (p == head) {
                    // 尝试获取资源
                    int r = tryAcquireShared(arg);
                    // 获取资源成功
                    if (r >= 0) {
                        // 这个名字起的很形象，setHead并且传播。
                        // 获取成功后将当前节点设为头节点(其实就是移除当前节点),并把结果传播出去,如果结果>0会做释放操作，唤醒后继节点
                        // >>> 这就是和独占模式不一样的地方，独占模式获取到资源后是去做自己的事情，直到事情干完再释放资源。
                        // >>> 而共享模式是只要还有剩余的资源就可以去做释放操作，来唤醒后继节点。
                        setHeadAndPropagate(node, r);
                        p.next = null; // help GC
                        // >>> 从这里来看，独占模式的acquireQueued(final Node node, int arg)和共享模式的doAcquireShared(int arg)
                        // >>> 这两个基本逻辑是一致的，是可以定义成一致的，我一般有这种强迫症。说明大神都是比较随性的吗？
                        if (interrupted)
                            selfInterrupt();
                        failed = false;
                        return;
                    }
                }
                // 这个和独占模式一样，都需要清理取消线程、设置通知标志，然后Park
                if (shouldParkAfterFailedAcquire(p, node) &&
                        parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            // 如果获取资源过程中失败（这里主要是tryAcquireShared过程中出现异常），取消当前线程
            if (failed)
                cancelAcquire(node);
        }
    }

    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }

    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }

    protected int tryAcquireShared(int arg) {
        throw new UnsupportedOperationException();
    }

    protected boolean tryReleaseShared(int arg) {
        throw new UnsupportedOperationException();
    }

    protected boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }

    /**
     * 独占模式下线程，获取共享资源的顶层入口
     */
    public final void acquire(int arg) {
        if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) {
            selfInterrupt();
        }
    }

    /**
     * 释放独占资源
     */
    public final boolean release(int arg) {
        // 尝试释放资源
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                // 从头节点开始唤醒在parkAndCheckInterrupt中Park住的节点
                unparkSuccessor(h);
            return true;
        }
        return false;
    }

    /**
     * 获取共享资源
     */
    public final void acquireShared(int arg) {
        /**
         * 获取共享资源：返回值的1、负数，表示没有获取到资源；2、0，获取到资源，并且表示资源已经消耗完毕；3、>0，获取到资源并且还有资源可用
         */
        if (tryAcquireShared(arg) < 0)
            // 如果没有获取到资源，需要进入队列排队获取
            doAcquireShared(arg);
    }

    /**
     * 释放共享资源
     */
    public final boolean releaseShared(int arg) {
        // 尝试释放资源
        if (tryReleaseShared(arg)) {
            // 释放成功，唤醒后继节点
            doReleaseShared();
            return true;
        }
        return false;
    }

    /**
     * 判断是否有线程比当前线程等待更久的时间,这个一般用在公平锁中判断当前线程是否有获取锁的资格
     */
    public final boolean hasQueuedPredecessors() {
        Node t = tail;
        Node h = head;
        Node s;
        return h != t &&
                ((s = h.next) == null || s.thread != Thread.currentThread());
    }
}
