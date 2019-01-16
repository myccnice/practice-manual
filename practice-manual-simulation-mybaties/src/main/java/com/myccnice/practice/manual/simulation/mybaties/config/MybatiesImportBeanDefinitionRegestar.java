package com.myccnice.practice.manual.simulation.mybaties.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import com.myccnice.practice.manual.simulation.mybaties.annotation.MapperScan;
import com.myccnice.practice.manual.simulation.mybaties.dao.CityDao;

public class MybatiesImportBeanDefinitionRegestar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(
            AnnotationMetadata importingClassMetadata,
            BeanDefinitionRegistry registry) {
        AnnotationAttributes annoAttrs =
                AnnotationAttributes.fromMap(
                        importingClassMetadata.getAnnotationAttributes(
                                MapperScan.class.getName()));
        List<String> basePackages = new ArrayList<String>();
        for (String pkg : annoAttrs.getStringArray("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        // ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
        //scanner.doScan(StringUtils.toStringArray(basePackages));
        // 把我们的对象加到spring
        // BeanDefinitionBuilder cityService = BeanDefinitionBuilder.genericBeanDefinition(CityService.class);
        // registry.registerBeanDefinition("cityService", cityService.getBeanDefinition());
        Class<?>[] clazzes = new Class[]{CityDao.class};
        for (Class<?> clazz : clazzes) {
            BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            AbstractBeanDefinition beanDefinition = bdb.getBeanDefinition();
            beanDefinition.setBeanClass(MybatiesFactoryBean.class);
            // beanDefinition.setBeanClassName(clazz.getName());
            registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
        }
    }

}
