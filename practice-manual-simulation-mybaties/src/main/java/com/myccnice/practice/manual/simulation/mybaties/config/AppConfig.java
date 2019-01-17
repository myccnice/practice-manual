package com.myccnice.practice.manual.simulation.mybaties.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.myccnice.practice.manual.simulation.mybaties.annotation.MapperScan;

@Configuration
@ComponentScan("com.myccnice.practice.manual.simulation.mybaties")
@MapperScan("com.myccnice.practice.manual.simulation.mybaties.dao")
public class AppConfig {

}
