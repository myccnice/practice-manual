package com.myccnice.practice.manual.simulation.mybaties.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.myccnice.practice.manual.simulation.mybaties.annotation.Select;

public class MybatiesInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Select select = method.getAnnotation(Select.class);
        System.out.println(select.value());
        return select;
    }

}
