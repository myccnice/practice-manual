package com.myccnice.practice.manual.jdk.java_util_fuction;

import org.junit.Test;

public class MyFuctionTest {

    @Test
    public void test() {
        // times的功能是对参数进行加倍
        MyFunction<Integer, Integer> times = e -> e * 2;
        // squar的功能是对参数进行乘方运算
        MyFunction<Integer, Integer> squar = e -> e * e;

        int t = 5;
        // 1、单独使用apply
        System.out.println(times.apply(t));
        System.out.println(squar.apply(t));
        // 2、组合使用compose
        System.out.println(times.compose(squar).apply(t));
        System.out.println(squar.compose(times).apply(t));
        // 3、组合使用andThen
        System.out.println(times.andThen(squar).apply(t));
        System.out.println(squar.andThen(times).apply(t));
        // 4、静态方法identity
        System.out.println(MyFunction.identity().apply(t));
    }
}
