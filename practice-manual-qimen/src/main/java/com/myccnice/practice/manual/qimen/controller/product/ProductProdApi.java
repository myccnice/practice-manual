package com.myccnice.practice.manual.qimen.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myccnice.practice.manual.qimen.service.product.ProductService;

/**
 * 奇门-速卖通-商品正式环境接口
 *
 * create in 2018年3月23日
 * @author wangpeng
 */
@RestController
@RequestMapping("/api/product")
public class ProductProdApi extends ProductApi {

    @Autowired
    @Qualifier("productProdService")
    private ProductService service;

    @Override
    protected ProductService getService() {
        return service;
    }
}
