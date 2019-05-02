package com.myccnice.practice.manual.jdk.java.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Key {

    public static final Comparator<Key> COMPARATOR = (k1, k2) -> {
        if (k1 == k2) {
            return 0;
        }
        if (k1 == null || k1.getId() == null) {
            return -1;
        }
        if (k2 == null || k2.getId() == null) {
            return 1;
        }
        return k1.getId().compareTo(k2.getId());
    };

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
        Map<Key, Integer> map = new TreeMap<>(COMPARATOR);
        map.put(null, 1);
        System.out.println(map.get(null));
    }
}
