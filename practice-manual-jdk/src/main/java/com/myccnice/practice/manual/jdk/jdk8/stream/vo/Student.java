package com.myccnice.practice.manual.jdk.jdk8.stream.vo;

import java.util.stream.Stream;

public class Student extends Person {

    /**
     * 学号
     */
    private String num;

    /**
     * 家长
     */
    Stream<Parent> parents;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Student() {

    }

    public Stream<Parent> getParents() {
        return parents;
    }

    public void setParents(Stream<Parent> parents) {
        this.parents = parents;
    }

    public Student(String name, int age, String num) {
        super(name, age);
        this.num = num;
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

    public static Stream<Parent> toParents(Student s) {
        return s.getParents();
    }
}
