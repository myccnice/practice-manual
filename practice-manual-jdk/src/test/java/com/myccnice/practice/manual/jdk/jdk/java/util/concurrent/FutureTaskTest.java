package com.myccnice.practice.manual.jdk.jdk.java.util.concurrent;

import java.util.concurrent.Callable;

import org.junit.Test;

import com.myccnice.practice.manual.jdk.java.util.concurrent.FutureTask;

public class FutureTaskTest {

    @Test
    public void taskTest() throws Exception {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        new Thread(task).start();
        Thread.sleep(100 * 1000);
        System.out.println(task.get());
    }
}
