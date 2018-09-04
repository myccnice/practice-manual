package com.myccnice.practice.manual.jdk.version8;

import org.junit.Test;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public class MySupplierTest {

    @Test
    public void test() {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            public Integer get() {
                System.out.println("执行get方法");
                return 1;
            };
        };
        Supplier<Integer> memoize = Suppliers.memoize(supplier);
        System.out.println(memoize.get());
        System.out.println(memoize.get());
    }
}
