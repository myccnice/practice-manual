package com.myccnice.practice.manual.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class SemaphoreTest {

    @Test
    public void tryAcquire() {
        Semaphore semaphore = new Semaphore(0);
        try {
            System.out.println(System.currentTimeMillis());
            semaphore.tryAcquire(1, TimeUnit.SECONDS);
            System.out.println(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
