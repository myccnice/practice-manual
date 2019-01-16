package com.myccnice.task.autotask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTask.class);

    protected void log(String taskName) {
        LOGGER.info(taskName);
    }

    protected void stop(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
