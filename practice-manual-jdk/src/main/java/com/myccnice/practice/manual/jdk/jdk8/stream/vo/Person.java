package com.myccnice.practice.manual.jdk.jdk8.stream.vo;

public class Person {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {

    }

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    
}
