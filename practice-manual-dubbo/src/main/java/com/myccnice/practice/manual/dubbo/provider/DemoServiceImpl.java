package com.myccnice.practice.manual.dubbo.provider;

import com.myccnice.practice.manual.common.exception.CustomException;
import com.myccnice.practice.manual.dubbo.facade.DemoService;

public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public void exceptionFilterTest(boolean pass) {
        if (pass) {
            return;
        }
        throw new CustomException("测试Dubbo返回CustomException");
    }

}
