package com.myccnice.practice.manual.qimen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标志该对象需要使用符合奇门格式的JSON串
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseQimenFastJson {

}
