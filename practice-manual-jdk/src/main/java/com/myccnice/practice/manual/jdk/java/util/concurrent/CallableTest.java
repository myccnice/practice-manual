package com.myccnice.practice.manual.jdk.java.util.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableTest implements Callable<Integer> {

    private static Random RANDOM = new Random();

    @Override
    public Integer call() throws Exception {
        System.out.println("执行call方法：" + Thread.currentThread().getName());
        Thread.sleep(10000);
        return RANDOM.nextInt();
    }

    public static void main(String[] args) throws Exception {
        CallableTest ct = new CallableTest();
        FutureTask<Integer> task = new FutureTask<>(ct);
        new Thread(task).start();
        Integer integer = task.get();
        System.out.println(integer);
    }
}
