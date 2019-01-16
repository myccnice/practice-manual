package com.myccnice.practice.manual.simulation.mybaties.config;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import com.myccnice.practice.manual.simulation.mybaties.dao.CityDao;

public class MybatiesFactoryBean implements FactoryBean<Object> {

    private Class<?> mapperInterface;

    @Override
    public Object getObject() throws Exception {
        if (mapperInterface == null) {
            mapperInterface = CityDao.class;
        }
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
