package com.myccnice.practice.manual.qimen.service.order;

import org.springframework.stereotype.Service;

import com.myccnice.practice.manual.qimen.service.AliexpressCallback;
import com.myccnice.practice.manual.qimen.service.AliexpressTemplate;
import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.myccnice.practice.manual.qimen.vo.SellerShipmentForTopDto;
import com.taobao.api.request.AliexpressLogisticsSellermodifiedshipmentfortopRequest;
import com.taobao.api.request.AliexpressLogisticsSellershipmentfortopRequest;
import com.taobao.api.request.AliexpressTradeNewRedefiningFindorderbyidRequest;
import com.taobao.api.request.AliexpressTradeNewRedefiningFindorderbyidRequest.AeopTpSingleOrderQuery;
import com.taobao.api.request.AliexpressTradeRedefiningFindorderlistqueryRequest;
import com.taobao.api.request.AliexpressTradeRedefiningFindorderlistqueryRequest.OrderListRequest;
import com.taobao.api.response.AliexpressLogisticsSellermodifiedshipmentfortopResponse;
import com.taobao.api.response.AliexpressLogisticsSellershipmentfortopResponse;
import com.taobao.api.response.AliexpressTradeNewRedefiningFindorderbyidResponse;
import com.taobao.api.response.AliexpressTradeRedefiningFindorderlistqueryResponse;

/**
 * AE-交易后端正式服务
 *
 * create in 2018年3月19日
 * @author wangpeng
 */
@Service("orderProdService")
public class OrderProdService implements OrderService {

    /**
     * 交易订单列表查询
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.3ZYEQg#?docType=2&docId=29998" />
     * @see <a href="https://qimen.taobao.com/?spm=0.0.0.0.Pxl1Sz#/officialApiManage/apiDocumentPre?appId=5810650&appkey=24669582&id=1662&officialApiId=33528&officialId=13587&_k=zcmpbq" />
     */
    @Override
    public QimenApiResponse findOrderListQuery(String token, OrderListRequest param) {
        AliexpressTradeRedefiningFindorderlistqueryRequest req = new AliexpressTradeRedefiningFindorderlistqueryRequest();
        req.setParam1(param);
        AliexpressTemplate<AliexpressTradeRedefiningFindorderlistqueryRequest> template = new AliexpressTemplate<>();
        return template.execute(req, token, new AliexpressCallback<AliexpressTradeRedefiningFindorderlistqueryResponse>() {
            public Object dataAnalysis(AliexpressTradeRedefiningFindorderlistqueryResponse response) {
                return response.getResult();
            };
        });
    }

    /**
     * 订单声明发货
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=30146">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0">奇门接口文档</a>
     */
    @Override
    public QimenApiResponse sellerShipmentForTop(String token, SellerShipmentForTopDto param) {
        AliexpressLogisticsSellershipmentfortopRequest req = new AliexpressLogisticsSellershipmentfortopRequest();
        req.setLogisticsNo(param.getLogisticsNo());
        req.setDescription(param.getDescription());
        req.setSendType(param.getSendType());
        req.setOutRef(param.getOutRef());
        req.setTrackingWebsite(param.getTrackingWebsite());
        req.setServiceName(param.getServiceName());
        AliexpressTemplate<AliexpressLogisticsSellershipmentfortopRequest> template = new AliexpressTemplate<>();
        return template.execute(req, token, new AliexpressCallback<AliexpressLogisticsSellershipmentfortopResponse>() {
            public Object dataAnalysis(AliexpressLogisticsSellershipmentfortopResponse response) {
                return response.getBody();
            };
        });
    }

    /**
     * 修改声明发货
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.NKV8PH#?docType=2&docId=30147">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0https://qimen.taobao.com/#/officialApiManage/requestConfig?appId=5810650&appkey=24669582&creatApi=true&id&officialApiId=33376&officialId=13587&_k=h601b0">奇门接口文档</a>
     */
    @Override
    public QimenApiResponse sellerModifiedShipmentForTop(String token, SellerShipmentForTopDto param) {
        AliexpressLogisticsSellermodifiedshipmentfortopRequest req = new AliexpressLogisticsSellermodifiedshipmentfortopRequest();
        req.setOldLogisticsNo(param.getOldLogisticsNo());
        req.setDescription(param.getDescription());
        req.setNewLogisticsNo(param.getNewLogisticsNo());
        req.setSendType(param.getSendType());
        req.setOutRef(param.getOutRef());
        req.setTrackingWebsite(param.getTrackingWebsite());
        req.setOldServiceName(param.getOldServiceName());
        req.setNewServiceName(param.getNewServiceName());
        AliexpressTemplate<AliexpressLogisticsSellermodifiedshipmentfortopRequest> template = new AliexpressTemplate<>();
        return template.execute(req, token, new AliexpressCallback<AliexpressLogisticsSellermodifiedshipmentfortopResponse>() {
            @Override
            public Object dataAnalysis(AliexpressLogisticsSellermodifiedshipmentfortopResponse response) {
                return response.getBody();
            }
        });
    }

    /**
     * 新版交易订单详情查询
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.3ZYEQg#?docType=2&docId=34813">速卖通文档</a>
     * @see <a href="https://qimen.taobao.com/#/officialApiManage/apiDocumentPre?appId=5810650&id=1660&officialApiId=33524&officialId=13587&_k=yh5f7g">奇门文档</a>
     */
    @Override
    public QimenApiResponse findOrderById(String token, AeopTpSingleOrderQuery param) {
        AliexpressTradeNewRedefiningFindorderbyidRequest req = new AliexpressTradeNewRedefiningFindorderbyidRequest();
        req.setParam1(param);
        AliexpressTemplate<AliexpressTradeNewRedefiningFindorderbyidRequest> template = new AliexpressTemplate<>();
        return template.execute(req, token, new AliexpressCallback<AliexpressTradeNewRedefiningFindorderbyidResponse>() {
            @Override
            public Object dataAnalysis(AliexpressTradeNewRedefiningFindorderbyidResponse response) {
                return response.getTarget();
            }
        });
    }
}
