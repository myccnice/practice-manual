package com.myccnice.practice.manual.qimen.interceptor;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.myccnice.practice.manual.qimen.annotation.UseQimenFastJson;
import com.myccnice.practice.manual.qimen.util.JSONUtils;

/**
 * 奇门接口重新对外的Fastjson for Spring MVC Converter.
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
public class QimenFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        UseQimenFastJson annotation = obj.getClass().getAnnotation(UseQimenFastJson.class);
        if (annotation != null) {
            obj = JSONUtils.writeToJSON(obj);
        }
        super.writeInternal(obj, outputMessage);
    }
}
