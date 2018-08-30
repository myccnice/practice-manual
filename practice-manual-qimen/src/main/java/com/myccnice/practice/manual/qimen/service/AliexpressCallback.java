package com.myccnice.practice.manual.qimen.service;

import com.taobao.api.TaobaoResponse;

/**
 * 解析速卖通接口正确返回值
 *
 * create in 2018年3月15日
 * @author wangpeng
 */
public interface AliexpressCallback<R extends TaobaoResponse> {

    /**
     * 将速卖通的数据解析成适合奇门的接口数据
     * @param response 速卖通接口响应
     * @return 奇门接口需要的数据值
     */
    Object dataAnalysis(R response);
}