package com.myccnice.practice.manual.qimen.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.myccnice.practice.manual.qimen.annotation.QimenRequestBody;
import com.myccnice.practice.manual.qimen.constant.App;

/**
 * 奇门requestBody参数解析器
 *
 * create in 2018年3月18日
 * @author wangpeng
 */
@Component
public class QimenAgumentsResolver implements HandlerMethodArgumentResolver {

    Logger logger = LoggerFactory.getLogger(QimenAgumentsResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(QimenRequestBody.class);
    }

    /**
     * 将request中的请求参数解析到当前Controller参数上
     * @param parameter 需要被解析的Controller参数，此参数必须首先传给{@link #supportsParameter}并返回true
     * @param mavContainer 当前request的ModelAndViewContainer
     * @param webRequest 当前request
     * @param binderFactory 生成{@link WebDataBinder}实例的工厂
     * @return 解析后的Controller参数
     */
    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        
        Object o = webRequest.getAttribute(App.REQUEST_BODY, 0);
        logger.info("resolveArgument=" + JSON.toJSONString(o));
        return o;
    }

}
