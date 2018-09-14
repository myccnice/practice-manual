package com.myccnice.practice.manual.jdk.java_util_fuction;

/**
 * 模拟{@link java.util.function.Supplier}
 * 这个接口只有一个{@code #get()}方法，看上去好像没什么用处。
 *
 * create in 2018年9月4日
 * @author wangpeng
 * @since 1.8
 */
@FunctionalInterface
public interface MySupplier<T> {

    T get();
}
