package com.myccnice.practice.manual.qimen.service.product;

import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.taobao.api.request.AliexpressPostproductRedefiningFindproductinfolistqueryRequest.AeopAeProductListQuery;

/**
 * 速卖通商品服务
 *
 * create in 2018年3月23日
 * @author wangpeng
 */
public interface ProductService {

    /**
     * 商品列表查询接口
     * @param token 速卖通acces token
     * @param query 查询条件
     * @return 奇门响应数据
     */
    QimenApiResponse findProductInfoListQuery(String token, AeopAeProductListQuery query);

    /**
     * 商品列表详情查询
     * @param token 速卖通acces token
     * @param query 查询条件
     * @return 奇门响应数据
     */
    QimenApiResponse queryProductDetailList(String token, AeopAeProductListQuery query);
}
