package com.myccnice.practice.manual.concurrent.util;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用加锁方式实现的伪随机数
 * @author chengmi
 */
public class ReentrantLockPseudoRandom extends PseudoRandom{

    private ReentrantLock lock = new ReentrantLock(false);
    private int seed;

    public ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }

    @Override
    int nextInt() {
        lock.lock();
        try {
            seed =  calculateNext(seed);
            return seed;
        } finally {
            lock.unlock();
        }
    }
}
