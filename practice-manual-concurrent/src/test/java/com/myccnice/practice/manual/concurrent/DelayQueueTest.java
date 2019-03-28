package com.myccnice.practice.manual.concurrent;

import java.util.Random;
import java.util.concurrent.DelayQueue;

import org.junit.Test;

import com.myccnice.practice.manual.concurrent.vo.Message;

public class DelayQueueTest {

    @Test
    public void delayQueue() {
        DelayQueue<Message> delayqueue = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Message message = new Message(i, "content" + i, random.nextInt(1000000));
            delayqueue.add(message);
        }
        System.out.println(Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Message message = null;
                    try {
                        message = delayqueue.take();
                        System.out.println("message = " + message.getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        System.out.println("finish");
    }
}
