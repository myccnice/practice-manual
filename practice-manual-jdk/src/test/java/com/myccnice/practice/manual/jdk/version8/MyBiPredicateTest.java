package com.myccnice.practice.manual.jdk.version8;

import org.junit.Test;

/**
 * {@link MyBiPredicate}测试类
 *
 * create in 2018年9月4日
 * @author wangpeng
 */
public class MyBiPredicateTest {

    @Test
    public void test() {
        int p1 = 1;
        int p2 = 2;
        MyBiPredicate<Integer, Integer> predicate = (a, b) -> {
            return a > b;
        };
        System.out.println(predicate.test(p1, p2));
    }
}
