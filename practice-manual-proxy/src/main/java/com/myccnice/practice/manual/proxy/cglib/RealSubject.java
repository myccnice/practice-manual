package com.myccnice.practice.manual.proxy.cglib;

/**
 * 真实的委托类
 * create in 2017年9月8日
 * @author wangpeng
 */
public class RealSubject {

    public void doSomething() {
        System.out.println("执行业务逻辑");
    }
}
