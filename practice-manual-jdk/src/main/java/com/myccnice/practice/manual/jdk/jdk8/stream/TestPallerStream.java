package com.myccnice.practice.manual.jdk.jdk8.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Person;
import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Student;

public class TestPallerStream {

    public static void main(String[] args) throws InterruptedException {
        // System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");
        // System.out.println(Runtime.getRuntime().availableProcessors());
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(new Person("name-" + i, i + 1));
        }
        //开始时间
        Instant start = Instant.now();
        List<Student> students = new ArrayList<>(persons.size());
//        for (Person person : persons) {
//            students.add(toStudent(person));
//        }
        Object lock = new Object();
        Consumer<Person> action = p -> {
            Student student = toStudent(p);
            synchronized (lock) {
                students.add(student);
            }
        };
        persons.parallelStream().peek(action).count();
        System.out.println(students.size());
        //结束时间
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());
    }

    public static Student toStudent(Person person) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student = new Student();
        student.setName(person.getName());
        student.setAge(person.getAge());
        student.setName(person.getName());
        return student;
    }
}
