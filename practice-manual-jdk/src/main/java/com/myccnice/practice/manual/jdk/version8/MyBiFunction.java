package com.myccnice.practice.manual.jdk.version8;

import java.util.Objects;

/**
 * 模拟{@link java.util.function.BiFunction}
 * 接收两个参数并返回一个结果的行为。
 *
 * create in 2018年9月3日
 * @author wangpeng
 * @see MyFunction
 * @since 1.8
 */
@FunctionalInterface
public interface MyBiFunction<T, U, R> {

    /**
     * 将两个参数用到给定的行为
     *
     * @param t 第一个参数
     * @param u 第二个参数
     * @return 行为的结果
     */
    R apply(T t, U u);

    /**
     * 可以将{@link MyFunction}和当前行为进行组合使用，并优先执行当前行为，返回当前行为类型
     *
     * @param after 后面执行的{@link MyFunction}行为
     * @return 组合后的当前行为类型
     */
    default <V> MyBiFunction<T, U, V> andThen(MyFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }
}
