package com.myccnice.practice.manual.concurrent.pool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest implements Callable<Integer> {

    private static Random RANDOM = new Random();

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());
        return RANDOM.nextInt();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CallableTest ct = new CallableTest();
        FutureTask<Integer> task = new FutureTask<>(ct);
        new Thread(task).start();
        Integer integer = task.get();
        System.out.println(integer);
    }
}
