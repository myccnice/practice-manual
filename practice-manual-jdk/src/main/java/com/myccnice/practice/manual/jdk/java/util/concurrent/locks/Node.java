package com.myccnice.practice.manual.jdk.java.util.concurrent.locks;

public final class Node {

    /**
     * 共享模式
     */
    static final Node SHARED = new Node();
    /**
     * 独占模式
     */
    static final Node EXCLUSIVE = null;

    /**
     * 表示当前节点被取消，被取消的节点在后续操作中会被忽略掉
     */
    static final int CANCELLED =  1;
    /**
     * 表示当前节点的的后继节点将要或者已经被阻塞，在当前节点释放的时候需要unpark后继节点
     */
    static final int SIGNAL    = -1;
    /**
     * 表示当前节点在等待condition，即在condition队列中
     */
    static final int CONDITION = -2;
    /**
     * 表示releaseShared需要被传播给后续节点（仅在共享模式下使用）
     */
    static final int PROPAGATE = -3;
    /**
     * 节点的等待状态
     * 默认0,无状态：表示当前节点在队列中等待获取锁。
     */
    volatile int waitStatus;

    /**
     * 前继节点
     */
    volatile Node prev;
    /**
     * 后继节点
     */
    volatile Node next;
    /**
     * 当前线程
     */
    volatile Thread thread;
    /**
     * 模式标志：独占 OR 共享
     * >> 这个名字怪怪的，为啥不直接叫mode
     */
    Node nextWaiter;

    final boolean isShared() {
        return nextWaiter == SHARED;
    }

    /**
     * 获取当前节点的前继节点
     */
    final Node predecessor() throws NullPointerException {
        Node p = prev;
        if (p == null) {
            throw new NullPointerException();
        } else {
            return p;
        }
    }

    Node() {

    }

    Node(Thread thread, Node mode) {
        this.nextWaiter = mode;
        this.thread = thread;
    }

    Node(Thread thread, int waitStatus) {
        this.waitStatus = waitStatus;
        this.thread = thread;
    }
}
