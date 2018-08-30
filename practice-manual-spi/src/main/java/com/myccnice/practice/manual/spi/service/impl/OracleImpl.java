package com.myccnice.practice.manual.spi.service.impl;

import com.myccnice.practice.manual.spi.service.SpiService;

public class OracleImpl implements SpiService {

    @Override
    public void execute() {
        System.out.println("Oracle");
    }

}
