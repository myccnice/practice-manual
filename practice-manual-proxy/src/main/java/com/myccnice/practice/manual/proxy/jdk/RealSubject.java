package com.myccnice.practice.manual.proxy.jdk;

/**
 * 真实的委托类
 * create in 2017年9月8日
 * @author wangpeng
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("执行业务逻辑");
    }

}
