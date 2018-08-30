package com.myccnice.practice.manual.qimen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 奇门请求参数requestBody注解。
 *
 * create in 2018年3月18日
 * @author wangpeng
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QimenRequestBody {

}
