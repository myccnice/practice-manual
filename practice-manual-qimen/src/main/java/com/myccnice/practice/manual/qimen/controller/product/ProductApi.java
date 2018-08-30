package com.myccnice.practice.manual.qimen.controller.product;

import org.springframework.web.bind.annotation.RequestMapping;

import com.myccnice.practice.manual.qimen.annotation.QimenRequestBody;
import com.myccnice.practice.manual.qimen.service.product.ProductService;
import com.myccnice.practice.manual.qimen.util.QimenParamUtils;
import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.taobao.api.request.AliexpressPostproductRedefiningFindproductinfolistqueryRequest.AeopAeProductListQuery;

/**
 * 奇门-速卖通-商品接口
 *
 * create in 2018年3月23日
 * @author wangpeng
 */
public abstract class ProductApi {

    protected abstract ProductService getService();

    /**
     * 商品列表查询接口
     *
     * 速卖通接口文档
     * @see <a href="https://developers.aliexpress.com/doc.htm?spm=a219a.7386653.0.0.Zk2Q5C#?docType=2&docId=30213"/>
     * 奇门接口文档
     * @see <a href="https://qimen.taobao.com/#/apiManage/apiDocumentPre?apiName=qimen.product.findproductinfolistquery&sceneId=13950&_k=mixo0o"/>
     */
    @RequestMapping("findproductinfolistquery")
    protected QimenApiResponse findProductInfoListQuery(String id, @QimenRequestBody String param1) {
        return getService().findProductInfoListQuery(id, QimenParamUtils.parseParam(AeopAeProductListQuery.class, param1));
    }

    /**
     * 商品详情列表查询
     */
    @RequestMapping("queryproductdetaillist")
    public QimenApiResponse queryProductDetailList(String id, @QimenRequestBody String param1) {
        return getService().queryProductDetailList(id, QimenParamUtils.parseParam(AeopAeProductListQuery.class, param1));
    }
}
