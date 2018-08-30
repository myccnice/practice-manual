package com.myccnice.practice.manual.qimen.controller.order;

import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.myccnice.practice.manual.qimen.annotation.QimenRequestBody;
import com.myccnice.practice.manual.qimen.service.order.OrderService;
import com.myccnice.practice.manual.qimen.util.QimenParamUtils;
import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.myccnice.practice.manual.qimen.vo.SellerShipmentForTopDto;
import com.taobao.api.request.AliexpressTradeNewRedefiningFindorderbyidRequest.AeopTpSingleOrderQuery;
import com.taobao.api.request.AliexpressTradeRedefiningFindorderlistqueryRequest.OrderListRequest;

/**
 * 奇门订单API接口
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
public abstract class OrderApi {

    protected abstract OrderService getService();

    /**
     * 交易订单列表查询
     * @param id 这里的id是accessToken
     * @param param1 通过request body传递的参数
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=29998">速卖通接口文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/apiDocumentPre?appId=5810650&appkey=24669582&id=1662&officialApiId=33528&officialId=13587&_k=svxz1t">奇门接口文档</a>
     */
    @RequestMapping("findOrderListQuery")
    public QimenApiResponse findOrderListQuery(String id, @QimenRequestBody String param1) {
        return getService().findOrderListQuery(id, QimenParamUtils.parseParam(OrderListRequest.class, param1));
    }

    /**
     * 声明发货接口
     * @param id 这里的id是accessToken
     * @param param1 通过request body传递的参数
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=30146">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0">奇门接口文档</a>
     */
    @RequestMapping("sellerShipmentForTop")
    public QimenApiResponse sellerShipmentForTop(String id, @QimenRequestBody String param1) {
        SellerShipmentForTopDto dto = JSONObject.parseObject(param1).toJavaObject(SellerShipmentForTopDto.class);
        return getService().sellerShipmentForTop(id, dto);
    }

    /**
     * 修改声明发货
     * @param id 这里的id是accessToken
     * @param param1 通过request body传递的参数
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=30147">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0">奇门接口文档</a>
     */
    @RequestMapping("sellerModifiedShipmentForTop")
    public QimenApiResponse sellerModifiedShipmentForTop(String id, @QimenRequestBody String param1) {
        return getService().sellerModifiedShipmentForTop(id, QimenParamUtils.parseParam(SellerShipmentForTopDto.class, param1));
    }

    /**
     * 新版交易订单详情查询
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.3ZYEQg#?docType=2&docId=34813">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/apiDocumentPre?appId=5810650&id=1660&officialApiId=33524&officialId=13587&_k=yh5f7g">奇门文档</a>
     */
    @RequestMapping("findOrderById")
    public QimenApiResponse findOrderById(String id, @QimenRequestBody String param1) {
        return getService().findOrderById(id, QimenParamUtils.parseParam(AeopTpSingleOrderQuery.class, param1));
    }
}
