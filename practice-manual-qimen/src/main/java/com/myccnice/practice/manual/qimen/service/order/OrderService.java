package com.myccnice.practice.manual.qimen.service.order;

import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.myccnice.practice.manual.qimen.vo.SellerShipmentForTopDto;
import com.taobao.api.request.AliexpressTradeNewRedefiningFindorderbyidRequest.AeopTpSingleOrderQuery;
import com.taobao.api.request.AliexpressTradeRedefiningFindorderlistqueryRequest.OrderListRequest;

/**
 * 速卖通订单服务
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
public interface OrderService {

    /**
     * 交易订单列表查询
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.3ZYEQg#?docType=2&docId=29998" />
     * @see <a href="https://qimen.taobao.com/?spm=0.0.0.0.Pxl1Sz#/officialApiManage/apiDocumentPre?appId=5810650&appkey=24669582&id=1662&officialApiId=33528&officialId=13587&_k=zcmpbq" />
     */
    QimenApiResponse findOrderListQuery(String token, OrderListRequest param);

    /**
     * 订单声明发货
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=30146">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0">奇门接口文档</a>
     */
    QimenApiResponse sellerShipmentForTop(String token, SellerShipmentForTopDto param);

    /**
     * 修改声明发货
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=30147">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0">奇门接口文档</a>
     */
    QimenApiResponse sellerModifiedShipmentForTop(String token, SellerShipmentForTopDto param);

    /**
     * 新版交易订单详情查询
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.3ZYEQg#?docType=2&docId=34813">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/apiDocumentPre?appId=5810650&id=1660&officialApiId=33524&officialId=13587&_k=yh5f7g">奇门文档</a>
     */
    QimenApiResponse findOrderById(String token, AeopTpSingleOrderQuery param);
}
