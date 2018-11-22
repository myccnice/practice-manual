package com.myccnice.practice.manual.jdk;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 系统测试类
 *
 * create in 2018年9月3日
 * @author wangpeng
 */
public class AppTest {
    public static void main(String[] args) {
        //开始时间
        Instant start = Instant.now();
        List<Integer> list = new ArrayList<>();
        for(int i =0 ; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        List<String> list2 = new ArrayList<>();
        Function<Integer, Integer> mapper = e -> {
            list2.add(e + "");
            return e;
        };
        Stream<Integer> map = list.stream().parallel().map(mapper);
        map.count();
        Consumer<Integer> action = e -> {};
        list.stream().parallel().peek(action).count();
        //结束时间
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());
    }
}
