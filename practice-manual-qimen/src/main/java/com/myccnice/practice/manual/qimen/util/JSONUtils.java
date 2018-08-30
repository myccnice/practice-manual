package com.myccnice.practice.manual.qimen.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.internal.util.json.JSONWriter;

/**
 * json相关工具类
 *
 * create in 2018年3月15日
 * @author wangpeng
 */
public class JSONUtils {

    private static final JSONWriter JSON_WRITER = new JSONWriter(false, true, true);


    public static final JSONObject writeToJSON(Object object) {
        if (object == null) {
            return new JSONObject();
        }
        return JSONObject.parseObject(JSON_WRITER.write(object));
    }

    public static final JSONArray writeToJSON(List<?> list) {
        if (CommonUtils.isEmpty(list)) {
            return null;
        }
        JSONArray array = new JSONArray(list.size());
        for (Object object : list) {
            if (object == null) {
                continue;
            }
            array.add(writeToJSON(object));
        }
        return array;
    }

    public static final JSONArray addToArray(JSONObject object) {
        JSONArray array = new JSONArray();
        array.add(object);
        return array;
    }

    public static final JSONObject getTemplateObject() {
        JSONObject resq = new JSONObject();
        resq.put("result_success", Boolean.TRUE);
        resq.put("error_code", "invalid-request");
        resq.put("error_desc", "System error");
        return resq;
    }

    public static final JSONObject newJSONObject(String key, Object value) {
        JSONObject resq = new JSONObject();
        resq.put(key, value);
        return resq;
    }

    public static final JSONObject merge(JSONObject arg1, JSONObject arg2) {
        if (arg1 == null) {
            return arg2;
        }
        if (arg2 == null) {
            return arg1;
        }
        for (Map.Entry<String, Object> entry : arg2.entrySet()) {
            arg1.put(entry.getKey(), entry.getValue());
        }
        return arg1;
    }
}
