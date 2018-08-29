package com.myccnice.practice.manual.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 该类实现了创建子类的方法与代理的方法。
 * getProxy(SuperClass.class)方法通过入参即父类的字节码，通过扩展父类的class来创建代理对象。
 *
 * create in 2017年9月13日
 * @author wangpeng
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class<?> clazz) {
        // 设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    /**
     * 拦截所有目标类方法的调用,通过代理类调用父类中的方法
     * @param obj 目标类的实例
     * @param method 目标类方法的反射对象
     * @param args 方法的动态入参
     * @param proxy 代理类实例
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("前置代理");
        // 通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("后置代理");
        return result;
    }
}
