package com.myccnice.practice.manual.jdk.java_util;

import org.junit.Test;

public class MyHashMapTest {

    @Test
    public void putTest() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("a", "a");
        map.put("a", "a");
        System.out.println(map.size());
    }
}
