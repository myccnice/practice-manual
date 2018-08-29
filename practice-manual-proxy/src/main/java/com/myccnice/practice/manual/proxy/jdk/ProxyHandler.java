package com.myccnice.practice.manual.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {

    /**
     * 被代理对象,即委托对象
     */
    private Object subject;

    public ProxyHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        // Q:这里的proxy对象是什么?
        // A:proxy是com.sun.proxy.Proxy的一个对象
        // Q:为什么直接调用proxy的toString方法会 stackoverflowerror
        // A:因为proxy对象就是我们真实的代理对象，从动态代理生成的源码中我们可以看到proxy对象会生成toString,hasCode,equeals三个方法
        //   其实现方式都是调用proxyHandler的invoke方法，就是当前方法，这就形成了一个死循环调用。最终调用层数达到极限，会报栈溢出。
        // proxy.toString();
        // 在转调具体目标对象之前，可以执行一些功能处理
        System.out.println("前置通知......");
        // 转调具体目标对象的方法
        Object object = method.invoke(subject, args);
        // 在转调具体目标对象之后，可以执行一些功能处理
        System.out.println("后置通知......");
        return object;
    }

    /**
     * 生成代理对象
     */
    public Object getProxy() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        return Proxy.newProxyInstance(loader, interfaces, this);
    }
}
