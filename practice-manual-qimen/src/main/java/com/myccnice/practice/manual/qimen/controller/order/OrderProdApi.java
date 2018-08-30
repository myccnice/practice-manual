package com.myccnice.practice.manual.qimen.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myccnice.practice.manual.qimen.service.order.OrderService;

/**
 * 奇门生成环境订单接口
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
@RestController
@RequestMapping("/api/order")
public class OrderProdApi extends OrderApi {

    @Autowired
    @Qualifier("orderProdService")
    private OrderService service;

    @Override
    protected OrderService getService() {
        return service;
    }
}
