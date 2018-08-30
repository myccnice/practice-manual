package com.myccnice.practice.manual.qimen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myccnice.practice.manual.qimen.constant.App;
import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.taobao.api.internal.spi.CheckResult;
import com.taobao.api.internal.spi.SpiUtils;

/**
 * 验签拦截器
 *
 * create in 2018年3月13日
 * @author wangpeng
 */
public class SignCheckInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignCheckInterceptor.class);
    private static final String API_URL_PREFIX = "/api";
    private static final String TEST_URL_PREFIX = "/test";

    // 在请求处理之前进行调用（Controller方法调用之前）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (!needCheckRequired(url)) {
            return true;
        }
        QimenApiResponse rsp = null;
        try {
            CheckResult checkSign = SpiUtils.checkSign(request, App.SECRET);
            if(checkSign.isSuccess()) {
                request.setAttribute(App.REQUEST_BODY, checkSign.getRequestBody());
                return true;
            } else {
                rsp = QimenApiResponse.signCheckFailResponse();
            }
        } catch (Exception e) {
            LOGGER.error("验签失败:" + e.getMessage(), e);
            rsp = QimenApiResponse.errorResponse(e.getMessage());
        }
        if (rsp != null) {
            response.getWriter().write(rsp.toString());
            response.getWriter().flush();
        }
        return false;
    }

    private boolean needCheckRequired(String url) {
        return url.startsWith(API_URL_PREFIX) || url.startsWith(TEST_URL_PREFIX);
    }

    // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    // 在整个请求结束之后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
