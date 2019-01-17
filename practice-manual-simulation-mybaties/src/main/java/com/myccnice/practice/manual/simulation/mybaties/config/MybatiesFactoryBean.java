package com.myccnice.practice.manual.simulation.mybaties.config;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

public class MybatiesFactoryBean implements FactoryBean<Object> {

    private Class<?> mapperInterface;

    public MybatiesFactoryBean(Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(
                MybatiesFactoryBean.class.getClassLoader(),
                new Class<?>[]{mapperInterface},
                new MybatiesInvocationHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

}
