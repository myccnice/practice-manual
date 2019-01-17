package com.myccnice.practice.manual.simulation.mybaties.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.myccnice.practice.manual.simulation.mybaties.config.MybatiesImportBeanDefinitionRegestar;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MybatiesImportBeanDefinitionRegestar.class)
public @interface MapperScan {

    String[] value() default {};
}
