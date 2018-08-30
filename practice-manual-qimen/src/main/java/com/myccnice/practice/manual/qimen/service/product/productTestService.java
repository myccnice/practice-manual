package com.myccnice.practice.manual.qimen.service.product;

import org.springframework.stereotype.Service;

import com.myccnice.practice.manual.qimen.util.CommonUtils;
import com.myccnice.practice.manual.qimen.vo.QimenApiResponse;
import com.qimencloud.api.scene9u96728k14.response.QimenProductQueryproductdetaillistResponse.JtongiProductDetailDto;
import com.taobao.api.request.AliexpressPostproductRedefiningFindproductinfolistqueryRequest.AeopAeProductListQuery;
import com.taobao.api.response.AliexpressPostproductRedefiningFindproductinfolistqueryResponse.AeopAeProductDisplaySampleDto;
import com.taobao.api.response.AliexpressPostproductRedefiningFindproductinfolistqueryResponse.AeopFindProductListResultDto;

/**
 * 速卖通商品服务-测试环境
 *
 * create in 2018年3月23日
 * @author wangpeng
 */
@Service("productTestService")
public class productTestService implements ProductService {

    @Override
    public QimenApiResponse findProductInfoListQuery(String token, AeopAeProductListQuery query) {
        AeopAeProductDisplaySampleDto sampleDto = new AeopAeProductDisplaySampleDto();
        sampleDto.setCurrencyCode("USD");
        sampleDto.setFreightTemplateId(0L);
        sampleDto.setGroupId(123L);
        sampleDto.setImageURLs("http://jtongi.cn/xxx.jsp");
        sampleDto.setOwnerMemberId("0");
        sampleDto.setOwnerMemberSeq(0L);
        sampleDto.setProductId(0L);
        sampleDto.setProductMaxPrice("0.0");
        sampleDto.setProductMinPrice("0.0");
        sampleDto.setSrc("0");
        sampleDto.setSubject("knew odd");
        sampleDto.setWsDisplay("expire_offline");
        AeopFindProductListResultDto resultDto = new AeopFindProductListResultDto();
        resultDto.setAeopAEProductDisplayDTOList(CommonUtils.initList(sampleDto));
        resultDto.setCurrentPage(10L);
        resultDto.setProductCount(1024L);
        resultDto.setTotalPage(10L);
        return QimenApiResponse.successResponse(resultDto);
    }

    @Override
    public QimenApiResponse queryProductDetailList(String token, AeopAeProductListQuery query) {
        JtongiProductDetailDto dto = new JtongiProductDetailDto();
        dto.setCurrencyCode("USD");
        dto.setSkuCode("cfas00978");
        dto.setSkuId("\"200000182:193;200007763:201336100\"");
        dto.setSkuImage("0");
        dto.setSkuPrice("200.07");
        dto.setSubject("knew odd");
        return QimenApiResponse.successResponse(CommonUtils.initList(dto));
    }

}
