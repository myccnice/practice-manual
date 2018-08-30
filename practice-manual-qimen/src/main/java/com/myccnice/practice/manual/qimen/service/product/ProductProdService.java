package com.myccnice.practice.manual.qimen.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.myccnice.practice.manual.qimen.service.AliexpressCallback;
import com.myccnice.practice.manual.qimen.service.AliexpressTemplate;
import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.qimencloud.api.scene9u96728k14.response.QimenProductQueryproductdetaillistResponse.JtongiProductDetailDto;
import com.taobao.api.ApiException;
import com.taobao.api.request.AliexpressPostproductRedefiningFindaeproductbyidRequest;
import com.taobao.api.request.AliexpressPostproductRedefiningFindproductinfolistqueryRequest;
import com.taobao.api.request.AliexpressPostproductRedefiningFindproductinfolistqueryRequest.AeopAeProductListQuery;
import com.taobao.api.response.AliexpressPostproductRedefiningFindaeproductbyidResponse;
import com.taobao.api.response.AliexpressPostproductRedefiningFindaeproductbyidResponse.AeopAeProductSku;
import com.taobao.api.response.AliexpressPostproductRedefiningFindaeproductbyidResponse.AeopFindProductResultDto;
import com.taobao.api.response.AliexpressPostproductRedefiningFindproductinfolistqueryResponse;
import com.taobao.api.response.AliexpressPostproductRedefiningFindproductinfolistqueryResponse.AeopAeProductDisplaySampleDto;
import com.taobao.api.response.AliexpressPostproductRedefiningFindproductinfolistqueryResponse.AeopFindProductListResultDto;

/**
 * 速卖通商品服务-正式环境
 *
 * create in 2018年3月23日
 * @author wangpeng
 */
@Service("productProdService")
public class ProductProdService implements ProductService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 商品列表查询接口
     * @param token 速卖通acces token
     * @param query 查询条件
     * @return 奇门响应数据
     */
    @Override
    public QimenApiResponse findProductInfoListQuery(String token, AeopAeProductListQuery query) {
        AliexpressPostproductRedefiningFindproductinfolistqueryRequest req = new AliexpressPostproductRedefiningFindproductinfolistqueryRequest();
        req.setAeopAEProductListQuery(query);
        AliexpressTemplate<AliexpressPostproductRedefiningFindproductinfolistqueryRequest> template = new AliexpressTemplate<>();
        return template.execute(req, token, new AliexpressCallback<AliexpressPostproductRedefiningFindproductinfolistqueryResponse>() {
            @Override
            public Object dataAnalysis(AliexpressPostproductRedefiningFindproductinfolistqueryResponse response) {
                return response.getResult();
            }
        });
    }

    @Override
    public QimenApiResponse queryProductDetailList(String token, AeopAeProductListQuery query) {
        // 先根据查询条件查询出商品简易列表
        AliexpressPostproductRedefiningFindproductinfolistqueryRequest req = new AliexpressPostproductRedefiningFindproductinfolistqueryRequest();
        req.setAeopAEProductListQuery(query);
        AliexpressTemplate<AliexpressPostproductRedefiningFindproductinfolistqueryRequest> template = new AliexpressTemplate<>();
        QimenApiResponse response = template.execute(req, token, new AliexpressCallback<AliexpressPostproductRedefiningFindproductinfolistqueryResponse>() {
            @Override
            public Object dataAnalysis(AliexpressPostproductRedefiningFindproductinfolistqueryResponse response) {
                return response.getResult();
            }
        });
        if (!response.getResultSuccess()) {
            return response;
        }
        AeopFindProductListResultDto result = (AeopFindProductListResultDto)response.getResultList();
        List<AeopAeProductDisplaySampleDto> displayDTOList = result.getAeopAEProductDisplayDTOList();
        List<JtongiProductDetailDto> list = new ArrayList<>();
        if (displayDTOList == null || displayDTOList.isEmpty()) {
            return QimenApiResponse.successResponse(list);
        }
        AliexpressPostproductRedefiningFindaeproductbyidRequest req2 = new AliexpressPostproductRedefiningFindaeproductbyidRequest();
        for (AeopAeProductDisplaySampleDto sampleDto : displayDTOList) {
            req2.setProductId(sampleDto.getProductId());
            // 取第一张图片
            String skuImage = sampleDto.getImageURLs();
            if (StringUtils.hasText(skuImage)) {
                skuImage = Arrays.asList(skuImage.trim().split(";")).get(0);
            }
            List<JtongiProductDetailDto> subList = findProductDetailList(req2, token);
            for (JtongiProductDetailDto jtongiProductDetailDto : subList) {
                jtongiProductDetailDto.setSkuImage(skuImage);
            }
            list.addAll(subList);
        }
        return QimenApiResponse.successResponse(list);
    }

    private List<JtongiProductDetailDto> findProductDetailList(AliexpressPostproductRedefiningFindaeproductbyidRequest request, String token) {
        List<JtongiProductDetailDto> list = new ArrayList<>();
        try {
            AliexpressPostproductRedefiningFindaeproductbyidResponse execute = AliexpressTemplate.getClient().execute(request, token);
            if (!execute.isSuccess()) {
                return list;
            }
            AeopFindProductResultDto dto = execute.getResult();
            for (AeopAeProductSku sku : dto.getAeopAeProductSKUs()) {
                JtongiProductDetailDto detailDto = new JtongiProductDetailDto();
                detailDto.setSkuCode(sku.getSkuCode());
                detailDto.setSubject(dto.getSubject());
                detailDto.setSkuId(sku.getId());
                detailDto.setCurrencyCode(sku.getCurrencyCode());
                detailDto.setSkuPrice(sku.getSkuPrice());
                list.add(detailDto);
            }
        } catch (ApiException e) {
            logger.error(e.getMessage());
        }
        return list;
    }
}
