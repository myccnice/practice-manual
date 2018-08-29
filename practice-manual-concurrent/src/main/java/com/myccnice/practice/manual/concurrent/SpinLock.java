package com.myccnice.practice.manual.concurrent;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 简单的自旋锁：自旋锁适用于锁占用时间短，即锁保护临界区很小的情景，同时它需要硬件级别操作，也要保证各缓存数据的一致性。
 *  另外，无法保证公平性，不保证先到先获得，可能造成线程饥饿。
 *  在多处理器机器上，每个线程对应的处理器都对同一个变量进行读写，而每次读写操作都将要同步每个处理器缓存，导致系统性能严重下降。
 * create in 2017年9月29日
 * @category 
 * @author wangpeng
 */
public class SpinLock {

    private static Unsafe unsafe = null;
    private static final long valueOffset;
    private volatile int value = 0;

    static {
        try {
            unsafe=getUnsafeInstance();
            valueOffset = unsafe.objectFieldOffset(SpinLock.class.getDeclaredField("value"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private static Unsafe getUnsafeInstance() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

    public void lock() {
        for (;;) {
            int newV = value + 1;
            if (unsafe.compareAndSwapInt(this, valueOffset, 0, newV)){
                return ;
            }
        }
    }

    public void unlock() {
        unsafe.compareAndSwapInt(this, valueOffset, 1, 0);
    }
}
