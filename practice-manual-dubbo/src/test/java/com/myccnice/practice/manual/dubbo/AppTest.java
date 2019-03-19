package com.myccnice.practice.manual.dubbo;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {

    private ClassPathXmlApplicationContext context;

    @Before
    private void init() {
        context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
    }

    protected <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    @After
    private void destroy() {
        if (context != null) {
            context.close();
        }
    }
}
