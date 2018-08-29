package com.myccnice.practice.manual.concurrent;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * CLH lock is Craig, Landin, and Hagersten (CLH) locks, CLH lock is a spin lock, can ensure no hunger, provide fairness first come first service. 
 * CLH是人名简写。这种锁本质是自旋锁，确保无饥饿、公平地先进先出竞争锁。AQS的内部实现机制就是CLH锁。
 *
 * create in 2018年8月29日
 * @author wangpeng
 * @see https://blog.csdn.net/zhengwei223/article/details/78147254
 */
public class CLHLock {

    private static Unsafe unsafe = null;
    private static final long valueOffset;
    private volatile CLHNode tail;

    public class CLHNode {
        private boolean isLocked = true;
    }

    static {
        try {
            unsafe = getUnsafeInstance();
            valueOffset = unsafe.objectFieldOffset(CLHLock.class.getDeclaredField("tail"));
        }catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public void lock(CLHNode currentThreadNode) {
        CLHNode preNode = null;
        for (;;) {
            preNode = tail;
            if (unsafe.compareAndSwapObject(this, valueOffset, tail, currentThreadNode))
                break;
        }
        if (preNode != null) {
            while (preNode.isLocked) {

            }
        }
    }

    public void unlock(CLHNode currentThreadNode) {
        if (!unsafe.compareAndSwapObject(this, valueOffset, currentThreadNode,null))
            currentThreadNode.isLocked = false;
    }

    private static Unsafe getUnsafeInstance() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

}
