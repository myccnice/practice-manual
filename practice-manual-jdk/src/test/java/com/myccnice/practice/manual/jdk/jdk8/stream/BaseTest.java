package com.myccnice.practice.manual.jdk.jdk8.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;

import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Parent;
import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Person;
import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Student;

public class BaseTest {

    protected List<Person> persons = new ArrayList<>();
    protected List<Student> students = new ArrayList<>();


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
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setAge(7 + i);
            student.setName("student-" + i);
            student.setNum("num-" + i);
            student.setParents(
                    Stream.of(new Parent("IdCardNo-M-" + i),
                            new Parent("IdCardNo-F-" + i)));
        }
        start = Instant.now();
    }

    @After
    public void end() {
        end = Instant.now();
        long seconds = Duration.between(start, end).getSeconds();
        if (seconds > 0) {
            System.out.println("本次测试耗时->" + seconds + "秒");
        } else {
            System.out.println("本次测试耗时->" + Duration.between(start, end).getNano() / 1000000 + "毫秒");
        }
    }
}
