package com.myccnice.practice.manual.concurrent.vo;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed{

    private Integer id;
    private String content;
    private long delay;       // 延迟时间
    private long exceptTime;  //执行时间

    public Message() {

    }

    public Message(Integer id, String content, long delay) {
        this.id = id;
        this.content = content;
        this.delay = delay;
        this.exceptTime = System.nanoTime() + delay;
    }

    @Override
    public int compareTo(Delayed delayed) {
        Message message = (Message) delayed;
        return this.exceptTime > message.getExceptTime() ? 1 : 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(exceptTime - System.nanoTime(), TimeUnit.SECONDS);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getExceptTime() {
        return exceptTime;
    }

    public void setExceptTime(long exceptTime) {
        this.exceptTime = exceptTime;
    }

    public static void main(String[] args) {
        DelayQueue<Message> delayqueue = new DelayQueue<>();
        for (int i = 1; i <= 10; i++) {
            Message message = new Message(i, "content" + i, i *  10000);
            delayqueue.add(message);
        }
        System.out.println(Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = null;
                while (true) {
                    try {
                        message = delayqueue.take();
                        if (message == null) {
                            break;
                        }
                        System.out.println("message = " + message.getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
