package com.myccnice.practice.manual.dubbo.provider;

import com.myccnice.practice.manual.dubbo.facade.DemoService;

public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name;
    }

}
