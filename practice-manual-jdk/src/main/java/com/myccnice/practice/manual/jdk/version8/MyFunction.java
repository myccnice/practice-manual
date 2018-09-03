package com.myccnice.practice.manual.jdk.version8;

import java.util.Objects;


/**
 * 模拟JDK8中的{@link java.util.function.Function}
 * Lambda表达式传递的是一种行为(或者说Lambda表达式是定义一种行为，而该行为实际还没发生),而该类的行为就是接受一个参数并产生结果。
 *
 * create in 2018年9月3日
 * @author wangpeng
 */
@FunctionalInterface
public interface MyFunction<T, R> {

    /**
     * 模拟{@link java.util.function.Function#apply()}
     *
     * @param t 种行为的参数
     * @return 行为返回的结果
     */
    R apply(T t);

    /**
     * 组合函数，可以将{@code before}和当前行为进行组合，先执行{@code before}
     *
     * @param before 先执行的行为
     * @return 一个组合行为
     */
    default <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * 组合函数，可以将{@code after}和当前行为组合使用，先执行当前函数
     * @param after 后执行的行为
     * @return 一个组合行为
     */
    default <V> MyFunction<T, V> andThen(MyFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * 同一性，即永远返回参数自身的一种行为。
     */
    static <T> MyFunction<T, T> identity() {
        return t -> t;
    }
}
