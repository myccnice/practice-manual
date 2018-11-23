package com.myccnice.practice.manual.jdk.jdk8.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Person;

public class BaseTest {

    protected List<Person> persons = new ArrayList<>();

    /**
     * 开始时间
     */
    private Instant start;
    /**
     * 结束时间
     */
    private Instant end;

    @Before
    public void start() {
        /**
         * 并发流默认使用forkjoin时，线程池的大小为Runtime.getRuntime().availableProcessors()
         * 可以使用下面的设置来改变，但这个改变时全局的，对所有流生效
         */
        // System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");
        for (int i = 0; i < 14; i++) {
            persons.add(new Person("name-" + i, i + 1));
        }
        start = Instant.now();
    }

    @After
    public void end() {
        end = Instant.now();
        System.out.println("本次测试耗时->" + Duration.between(start, end).getSeconds() + "秒");
    }
}
