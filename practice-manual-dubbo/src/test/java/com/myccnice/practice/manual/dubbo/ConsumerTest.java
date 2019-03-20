package com.myccnice.practice.manual.dubbo;

import org.junit.Test;

import com.alibaba.dubbo.rpc.RpcException;
import com.myccnice.practice.manual.common.exception.CustomException;
import com.myccnice.practice.manual.dubbo.facade.DemoService;

public class ConsumerTest extends AppTest {

    @Test
    public void exceptionFilterTest() {
        DemoService service = getBean(DemoService.class);
        try {
            service.exceptionFilterTest(false);
        } catch (CustomException e) {
            System.out.println("CustomException");
        } catch (RpcException e) {
            System.out.println("RpcException");
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println("Exception");
        }
        
    }
}
