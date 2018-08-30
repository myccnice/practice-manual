package com.myccnice.practice.manual.qimen.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myccnice.practice.manual.qimen.service.order.OrderService;

/**
 * 奇门订单测试环境
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
@RestController
@RequestMapping("/test/order")
public class OrderTestApi extends OrderApi {

    @Autowired
    @Qualifier("orderTestService")
    private OrderService service;

    @Override
    protected OrderService getService() {
        return service;
    }
}
