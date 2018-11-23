package com.myccnice.practice.manual.jdk.jdk8.stream.vo;

public class Student extends Person {

    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Student() {

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
}
