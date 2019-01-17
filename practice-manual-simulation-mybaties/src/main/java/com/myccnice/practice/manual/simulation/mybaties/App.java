package com.myccnice.practice.manual.simulation.mybaties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myccnice.practice.manual.simulation.mybaties.config.AppConfig;
import com.myccnice.practice.manual.simulation.mybaties.service.CityService;

public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(CityService.class).query();
        context.close();
    }
}
