package com.myccnice.practice.manual.dubbo.consumer;

import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.myccnice.practice.manual.dubbo.facade.DemoService;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        demoService.sayHello("world"); // 执行远程方法
        Future<String> future = RpcContext.getContext().getFuture();
        System.out.println(future.get()); // 显示调用结果
        context.close();
    }
}