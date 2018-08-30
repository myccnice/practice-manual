package com.myccnice.practice.manual.qimen.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 奇门接口参数相关工具类
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
public class QimenParamUtils {

    /**
     * 奇门request body参数名称
     */
    private static final String PARAM_NAME = "param1";

    /**
     * 将JSON字符串解析为对象
     *
     * @param clazz 对象类型
     * @param param 字符串参数
     * @return 参数对象
     */
    public static <T> T parseParam(Class<T> clazz, String param) {
        return parseParam(clazz, param, PARAM_NAME);
    }

    /**
     * 将字符串类型的参数解析为对象
     *
     * @param clazz 对象类型
     * @param param 字符串参数
     * @param key 关键字
     * @return 参数对象
     */
    public static <T> T parseParam(Class<T> clazz, String param, String key) {
        JSONObject object = JSONObject.parseObject(param);
        if (object == null) {
            return null;
        }
        return JSONObject.parseObject(object.getString(key), clazz);
    }
}
