package com.myccnice.practice.manual.jdk.java_util_fuction;

import java.util.Comparator;
import java.util.Objects;

/**
 * 模拟{@link java.util.function.BinaryOperator}
 * 它其实是一种特殊的{@link java.util.function.BiFunction}。它的两个参数和返回值类型是一样的。
 * 并且它带有两个静态的方法{@code #minBy(Comparator comparator)}和{@code #maxBy(Comparator comparator)}
 *
 * create in 2018年9月4日
 * @author wangpeng
 */
@FunctionalInterface
public interface MyBinaryOperator<T> extends MyBiFunction<T, T, T> {

    /**
     * 根据比较器选择出较小者的行为
     *
     * @param comparator 指定的比较器
     * @return 返回两者中较小元素的行为
     */
    public static <T> MyBinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * 根据比较器选择出较大者的行为
     *
     * @param comparator 指定的比较器
     * @return 返回两者中较大元素的行为
     */
    public static <T> MyBinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}
