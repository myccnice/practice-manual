package com.myccnice.practice.manual.redisson;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

/**
 * Unit test for simple App.
 */
public class AppTest {

    long internalLockLeaseTime = 30 * 1000;
    int a = 0;
    HashedWheelTimer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), internalLockLeaseTime, TimeUnit.MILLISECONDS, 1024, false);

    @Test
    public void netty() {
        timer = new HashedWheelTimer(10, TimeUnit.MILLISECONDS, 8);
        System.out.println(Thread.currentThread().getName() + System.currentTimeMillis());
        scheduleExpirationRenewal();
    }

    private void scheduleExpirationRenewal() {
        if (a > 5) {
            return;
        }
        Timeout task = timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                a++;
                System.out.println(Thread.currentThread().getName() + System.currentTimeMillis());
            }
        }, 10, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(10 * 10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (a == 5) {
            task.cancel();
        }
    }
}
