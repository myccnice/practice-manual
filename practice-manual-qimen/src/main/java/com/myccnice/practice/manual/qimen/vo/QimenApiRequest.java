package com.myccnice.practice.manual.qimen.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * 对奇门接口访问参数的封装
 *
 * create in 2018年3月16日
 * @author wangpeng
 */
public class QimenApiRequest {

    /**
     * 卖家登录的login_id
     */
    private String id;

    /**
     * 访问时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，例如：2015-01-01 12:00:00。淘宝API服务端允许客户端请求最大时间误差为10分钟
     */
    private String timestamp;

    /**
     * API输入参数签名结果
     */
    private String sign;

    /**
     * 应用的AppKey
     */
    private String app_key;

    /**
     * 接口方法
     */
    private String method;
    /**
     * 应用的AppSecret
     */
    private String app_secret;

    /**
     * 访问目标的AppKey
     */
    private String target_app_key;

    /**
     * 应用的授权访问token
     */
    private String token;

    /**
     * 返回值格式：xml、json
     */
    private String format;

    /**
     * 签名方法
     */
    private String sign_method;

    /**
     * 版本号
     */
    private String v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getTarget_app_key() {
        return target_app_key;
    }

    public void setTarget_app_key(String target_app_key) {
        this.target_app_key = target_app_key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSign_method() {
        return sign_method;
    }

    public void setSign_method(String sign_method) {
        this.sign_method = sign_method;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
