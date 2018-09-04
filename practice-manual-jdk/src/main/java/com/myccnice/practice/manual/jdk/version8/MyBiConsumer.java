package com.myccnice.practice.manual.jdk.version8;

import java.util.Objects;

/**
 * 模拟{@link java.util.function.BiConsumer}
 * 接收两个参数没有返回值的行为。
 *
 * create in 2018年9月4日
 * @author wangpeng
 */
@FunctionalInterface
public interface MyBiConsumer<T, U> {

    /**
     * 对两个参数执行某种操作
     * @param t 第一个参数
     * @param u 第二个参数
     */
    void accept(T t, U u);

    /**
     * 串联操作。将当前行为和{@code after}行为按顺序执行
     * @param after 后面要执行的行为
     * @return 组合行为
     */
    default MyBiConsumer<T, U> andThen(MyBiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return (t, u) -> {
            accept(t, u);
            after.accept(t, u);
        };
    }
}
