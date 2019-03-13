package com.myccnice.practice.manual.nibenjia.unsafe;

public class PrivateClass {

    public int intfield ;

    public static int staticIntField;

    public static int[] arr;

    static {
        System.out.println("执行静态代码块");
    }

    private PrivateClass() {
        intfield = 1;
        System.out.println("执行构造方法");
    }

    public PrivateClass(int intfield) {
        this();
        this.intfield = intfield;
    }
}
