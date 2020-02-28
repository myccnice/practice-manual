package com.myccnice.practice.manual.jdk.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

public class DistinctTest {

    @Test
    public void distinct() {
        List<String> strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("Hello");
        strList.add("Hello");
        strList.add("world");
        // List<String> disList = strList.stream().distinct().collect(Collectors.toList());
        // System.out.println(disList);
        List<String> collect = strList.stream().filter(distinctByKey(s -> s)).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
         return t -> seen.add(keyExtractor.apply(t));
    }
}
