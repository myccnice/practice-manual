package com.myccnice.practice.manual.proxy.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 执行RealSubject对象的方法之前的处理意见
 * Created by louis on 2016/4/14.
 */
public class TicketServiceBeforeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        System.out.println("BEFORE_ADVICE: 欢迎光临代售点....");
    }
}
