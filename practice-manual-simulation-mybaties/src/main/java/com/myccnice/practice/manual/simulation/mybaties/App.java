package com.myccnice.practice.manual.simulation.mybaties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.myccnice.practice.manual.simulation.mybaties.config.AppConfig;
import com.myccnice.practice.manual.simulation.mybaties.service.CityService;

@EnableAspectJAutoProxy
public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(CityService.class).query();
        context.close();
    }
}
