package com.myccnice.practice.manual.qimen.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 通用工具类。
 *
 * create in 2018年3月15日
 * @author wangpeng
 */
public class CommonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 判断集合是否为空
     */
    public static final boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 打印出请求中的所有参数
     */
    public static final void printRequestParam(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        JSONObject object = new JSONObject();
        while (parameterNames.hasMoreElements()) {
            String string = parameterNames.nextElement();
            object.put(string, request.getParameter(string));
        }
        LOGGER.info("From getParameterNames->" + object.toJSONString());
        object.clear();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null && !parameterMap.isEmpty()) {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                object.put(entry.getKey(), entry.getValue());
            }
        }
        LOGGER.info("From getParameterMap->" + object.toJSONString());
    }

    /**
     * 将json数组转化成用逗号分隔的字符串
     */
    public static final String arrayToString(JSONArray jsonArray) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < jsonArray.size(); i++) {
            sb.append(jsonArray.getString(i));
            if(i + 1 < jsonArray.size()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 新建一个集合，并初始化一个元素
     * @param e 初始化的元素
     * @return 集合
     */
    public static final <E> List<E> initList(E e) {
        List<E> list = new ArrayList<>();
        list.add(e);
        return list;
    }
}
