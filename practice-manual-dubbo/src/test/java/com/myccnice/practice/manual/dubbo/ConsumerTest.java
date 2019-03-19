package com.myccnice.practice.manual.dubbo;

import org.junit.Test;

import com.myccnice.practice.manual.dubbo.facade.DemoService;

public class ConsumerTest extends AppTest {

    @Test
    public void exceptionFilterTest() {
        DemoService service = getBean(DemoService.class);
        service.exceptionFilterTest(false);
    }
}
