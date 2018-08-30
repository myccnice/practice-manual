package com.myccnice.practice.manual.spi.service.impl;

import com.myccnice.practice.manual.spi.service.SpiService;

public class MysqlService implements SpiService {

    @Override
    public void execute() {
        System.out.println("Mysql");
    }

}
