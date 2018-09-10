package com.myccnice.practice.manual.dubbo.vo;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 2475521841299503430L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}