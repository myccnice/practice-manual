package com.myccnice.practice.manual.zeromvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.myccnice.practice.manual.zeromvc.controller")
public class WebConfig {

}
