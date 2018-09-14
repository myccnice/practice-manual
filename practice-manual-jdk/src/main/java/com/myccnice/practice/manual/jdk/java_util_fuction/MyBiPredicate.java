package com.myccnice.practice.manual.jdk.java_util_fuction;

/**
 * 模拟{@link java.util.function.BiPredicate}
 * 这是对两个参数做逻辑判断，返回真或者假的一种行为
 *
 * create in 2018年9月4日
 * @author wangpeng
 */
@FunctionalInterface
public interface MyBiPredicate<T, U> {

    /**
     * 逻辑判断
     */
    boolean test(T t, U u);

    /**
     * 组合行为：逻辑与
     *
     * @param other 另一个逻辑行为
     * @return 组合逻辑与
     */
    default MyBiPredicate<T, U> and(MyBiPredicate<? super T, ? super U> other) {
        return (t, u) -> test(t, u) && other.test(t, u);
    }

    /**
     * 组合行为：逻辑或
     *
     * @param other 另一个逻辑行为
     * @return 组合逻辑或
     */
    default MyBiPredicate<T, U> or(MyBiPredicate<? super T, ? super U> other) {
        return (t, u) -> test(t, u) || other.test(t, u);
    }

}
