package com.myccnice.practice.manual.proxy.aop;

import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

import com.myccnice.practice.manual.proxy.aop.advice.TicketServiceAfterReturningAdvice;
import com.myccnice.practice.manual.proxy.aop.advice.TicketServiceAroundAdvice;
import com.myccnice.practice.manual.proxy.aop.advice.TicketServiceBeforeAdvice;
import com.myccnice.practice.manual.proxy.aop.advice.TicketServiceThrowsAdvice;

public class SpringAOPTest {

    @Test
    public void app() {
        // 1.针对不同的时期类型，提供不同的Advice
        Advice beforeAdvice = new TicketServiceBeforeAdvice();
        Advice afterReturningAdvice = new TicketServiceAfterReturningAdvice();
        Advice aroundAdvice = new TicketServiceAroundAdvice();
        Advice throwsAdvice = new TicketServiceThrowsAdvice();

        RailwayStation railwayStation = new RailwayStation();

        // 2.创建ProxyFactoryBean,用以创建指定对象的Proxy对象
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        // 3.设置Proxy的接口
        proxyFactoryBean.setInterfaces(TicketService.class);
        // 4. 设置RealSubject
        proxyFactoryBean.setTarget(railwayStation);
        // 5.使用JDK基于接口实现机制的动态代理生成Proxy代理对象，如果想使用CGLIB，需要将这个flag设置成true
        proxyFactoryBean.setProxyTargetClass(true);

        // 6. 添加不同的Advice
        proxyFactoryBean.addAdvice(afterReturningAdvice);
        proxyFactoryBean.addAdvice(aroundAdvice);
        proxyFactoryBean.addAdvice(throwsAdvice);
        proxyFactoryBean.addAdvice(beforeAdvice);
        proxyFactoryBean.setProxyTargetClass(false);
        // 7通过ProxyFactoryBean生成Proxy对象
        TicketService ticketService = (TicketService) proxyFactoryBean.getObject();
        ticketService.sellTicket();
        ticketService.inquire();
    }
}
