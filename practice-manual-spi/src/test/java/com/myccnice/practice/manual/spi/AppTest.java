package com.myccnice.practice.manual.spi;

import java.util.Iterator;

import org.junit.Test;

import com.myccnice.practice.manual.spi.service.SpiService;
import com.myccnice.practice.manual.spi.util.ServiceLoader;

public class AppTest {

    @Test
    public void spiTest() {
        ServiceLoader<SpiService> loader = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> it = loader.iterator();
        while (it.hasNext()) {
            SpiService next = it.next();
            next.execute();
        }
    }
}