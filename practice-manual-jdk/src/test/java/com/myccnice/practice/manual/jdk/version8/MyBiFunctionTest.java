package com.myccnice.practice.manual.jdk.version8;

import org.junit.Test;

public class MyBiFunctionTest {

    @Test
    public void test() {
        // 计算矩形面积
        MyBiFunction<Integer, Integer, Integer> getPer = (length, width) -> {
            return 2 * (length + width);
        };
        // 根据菱形周期求边长
        MyFunction<Integer, Double> getLen = per -> {
            return per / 4.0;
        };
        int len = 2;
        int wid = 3;
        // 1、计算2X3的矩形面积
        System.out.println(getPer.apply(len, wid));
        // 2、测试andThen
        System.out.println(getPer.andThen(getLen).apply(len, wid));
    }
}
