package com.myccnice.task.autotask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Async
@Component
public class Task2 extends BaseTask {

    @Scheduled(cron="*/5 * * * * ?")
    public void task1() {
        log("Task2#task1");
        stop(2);
    }

    @Scheduled(fixedDelay = 5000)
    public void task2() {
        log("Task2#task2");
        stop(4);
    }
}
