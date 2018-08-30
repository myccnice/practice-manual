package com.myccnice.practice.manual.qimen.service.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import sun.misc.IOUtils;

import com.alibaba.fastjson.JSONObject;
import com.myccnice.practice.manual.qimen.util.JSONUtils;
import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.myccnice.practice.manual.qimen.vo.SellerShipmentForTopDto;
import com.taobao.api.request.AliexpressTradeNewRedefiningFindorderbyidRequest.AeopTpSingleOrderQuery;
import com.taobao.api.request.AliexpressTradeRedefiningFindorderlistqueryRequest.OrderListRequest;
import com.taobao.api.response.AliexpressTradeNewRedefiningFindorderbyidResponse;
import com.taobao.api.response.AliexpressTradeRedefiningFindorderlistqueryResponse.Money;
import com.taobao.api.response.AliexpressTradeRedefiningFindorderlistqueryResponse.OrderItemVo;
import com.taobao.api.response.AliexpressTradeRedefiningFindorderlistqueryResponse.OrderListVo;
import com.taobao.api.response.AliexpressTradeRedefiningFindorderlistqueryResponse.OrderProductVo;

/**
 * AE-交易后端测试服务
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
@Service("orderTestService")
public class OrderTestService implements OrderService{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTestService.class);

    @Value(value="classpath:static/data/findOrderByIdRespExample.json")
    private Resource findOrderByIdResource;

    /**
     * 交易订单列表查询
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.3ZYEQg#?docType=2&docId=29998" />
     * @see <a href="https://qimen.taobao.com/?spm=0.0.0.0.Pxl1Sz#/officialApiManage/apiDocumentPre?appId=5810650&appkey=24669582&id=1662&officialApiId=33528&officialId=13587&_k=zcmpbq" />
     */
    public QimenApiResponse findOrderListQuery(String token, OrderListRequest param) {
        // 打印请求参数
        LOGGER.info(JSONUtils.writeToJSON(param).toJSONString());
        // 构造测试数据
        Money money = new Money();
        money.setCurrencyCode("USD");

        OrderProductVo productVo = new OrderProductVo();
        productVo.setOrderId(1160045860056286L);
        productVo.setLogisticsAmount(money);
        productVo.setProductName("auto test product");

        List<OrderProductVo> productList = new ArrayList<>();
        productList.add(productVo);

        OrderItemVo itemVo = new OrderItemVo();
        itemVo.setLogisitcsEscrowFeeRate("0.01");
        itemVo.setOrderId(1160045860056286L);
        itemVo.setProductList(productList);

        List<OrderItemVo> orderList = new ArrayList<>();
        orderList.add(itemVo);

        OrderListVo result = new OrderListVo();
        result.setOrderList(orderList);
        result.setTotalItem(1L);
        return QimenApiResponse.successResponse(result);
    }

    /**
     * 订单声明发货
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=30146">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0">奇门接口文档</a>
     */
    @Override
    public QimenApiResponse sellerShipmentForTop(String token, SellerShipmentForTopDto param) {
        return QimenApiResponse.successResponse("{\"result_success\":true}");
    }

    /**
     * 修改声明发货
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=30147">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0">奇门接口文档</a>
     */
    @Override
    public QimenApiResponse sellerModifiedShipmentForTop(String token, SellerShipmentForTopDto param) {
        return QimenApiResponse.successResponse("{\"result_success\":true}");
    }

    /**
     * 新版交易订单详情查询
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.3ZYEQg#?docType=2&docId=34813">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/apiDocumentPre?appId=5810650&id=1660&officialApiId=33524&officialId=13587&_k=yh5f7g">奇门文档</a>
     */
    @Override
    public QimenApiResponse findOrderById(String token, AeopTpSingleOrderQuery param) {
        try {
            byte[] readFully = IOUtils.readFully(findOrderByIdResource.getInputStream(), -1, true);
            JSONObject json = JSONObject.parseObject(new String(readFully));
            AliexpressTradeNewRedefiningFindorderbyidResponse response = json.toJavaObject(AliexpressTradeNewRedefiningFindorderbyidResponse.class);
            return QimenApiResponse.successResponse(response.getTarget());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return QimenApiResponse.errorResponse(e.getMessage());
        }
    }
}
