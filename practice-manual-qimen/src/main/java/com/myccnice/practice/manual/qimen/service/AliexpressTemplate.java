package com.myccnice.practice.manual.qimen.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.myccnice.practice.manual.qimen.constant.App;
import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;

/**
 * 调用速卖通接口模板类。调用步骤如下：
 *  1、构造TaobaoClient
 *  2、调用速卖通接口，获取返回值
 *  3、解析速卖通接口返回值，设置到奇门接口中
 * 
 * create in 2018年3月15日
 * @author wangpeng
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AliexpressTemplate <R extends TaobaoRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliexpressTemplate.class);
    // 1、构造TaobaoClient
    private static final TaobaoClient CLIENT = new DefaultTaobaoClient(App.JST_URL, App.APP_KEY, App.SECRET);

    public final QimenApiResponse execute(R req, String token, AliexpressCallback callback) {
        LOGGER.info("token" + token);
        // 2、调用接口获取返回值的body
        TaobaoResponse rsp = null;
        try {
            rsp = CLIENT.execute(req, token);
        } catch (ApiException e) {
            LOGGER.error("速卖通接口异常：" + e.getMessage(), e);
            return new QimenApiResponse(e.getErrCode(), Boolean.FALSE, e.getErrMsg());
        } catch (Exception e) {
            LOGGER.error("我司程序异常：" + e.getMessage(), e);
            return QimenApiResponse.errorResponse(e.getMessage());
        }
        // top接口返回失败
        if (!rsp.isSuccess()) {
            LOGGER.error(rsp.getBody());
            LOGGER.error("token:" + token + ",ApiMethodName:" + req.getApiMethodName());
            QimenApiResponse error = new QimenApiResponse(rsp.getErrorCode(), Boolean.FALSE, rsp.getMsg() + ":" + rsp.getSubMsg());
            error.setErrorDesc(rsp.getSubMsg());
            error.setResultErrorCode(rsp.getErrorCode());
            error.setResultSuccess(Boolean.FALSE);
            JSONObject result = new JSONObject();
            result.put("error_desc", rsp.getBody());
            result.put("error_code", rsp.getErrorCode());
            result.put("success", Boolean.FALSE);
            error.setResult(result);
            return error;
        }
        // 3、解析速卖通接口返回值，设置到奇门接口中
        // 注意此处返回成功表示奇门接口调用成功，不代表操作一定成功。查询类奇门接口返回成功，一般而言表示查询操作成功，修改类操作，需要根据奇门接口的result判断操作是否成功。
        return QimenApiResponse.successResponse(callback.dataAnalysis(rsp));
    }

    public static TaobaoClient getClient() {
        return CLIENT;
    }
}
