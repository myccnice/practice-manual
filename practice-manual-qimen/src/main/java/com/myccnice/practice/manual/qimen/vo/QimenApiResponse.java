package com.myccnice.practice.manual.qimen.vo;

import java.io.Serializable;
import java.util.Collection;

import com.alibaba.fastjson.JSONObject;
import com.myccnice.practice.manual.qimen.annotation.UseQimenFastJson;
import com.myccnice.practice.manual.qimen.util.JSONUtils;
import com.taobao.api.internal.mapping.ApiField;

/**
 * 奇门接口相应数据
 *
 * create in 2018年3月16日
 * @author wangpeng
 */
@UseQimenFastJson
public class QimenApiResponse implements Serializable {

    private static final long serialVersionUID = 5014379068811962022L;

    private static final String FAILED_CODE = "500";
    private static final String SIGN_CHECK_FAILURE = "sign-check-failure";
    private static final String ILLEGAL_REQUEST = "Illegal request";
    private static final String FAILURE = "failure";

    /**
     * 状态码
     */
    @ApiField("error_code")
    private String errorCode;

    /**
     * 错误信息
     */
    @ApiField("error_message")
    private String errorMessage;

    /**
     * 错误描述
     */
    @ApiField("error_desc")
    private String errorDesc;

    /**
     * 错误码
     */
    @ApiField("result_error_code")
    private String resultErrorCode;

    /**
     * 错误描述
     */
    @ApiField("result_error_desc")
    private String resultErrorDesc;

    /**
     * 调用结果
     */
    @ApiField("result_success")
    private Boolean resultSuccess;

    /**
     * 二级错误信息
     */
    @ApiField("sub_message")
    private String subMessage;

    /**
     * 响应失败出参判断条件
     */
    @ApiField("flag")
    private String flag;

    /**
     * 二级错误码
     */
    @ApiField("sub_code")
    private String subCode;

    /**
     * 出参
     */
    @ApiField("result")
    private Object result;

    /**
     * 出参
     */
    @ApiField("result_list")
    private Object resultList;

    /**
     * 出参
     */
    @ApiField("is_success")
    private Boolean isSuccess;

    public QimenApiResponse(){

    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getResultErrorCode() {
        return resultErrorCode;
    }

    public void setResultErrorCode(String resultErrorCode) {
        this.resultErrorCode = resultErrorCode;
    }

    public String getResultErrorDesc() {
        return resultErrorDesc;
    }

    public void setResultErrorDesc(String resultErrorDesc) {
        this.resultErrorDesc = resultErrorDesc;
    }

    public Boolean getResultSuccess() {
        return resultSuccess;
    }

    public void setResultSuccess(Boolean resultSuccess) {
        this.resultSuccess = resultSuccess;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResultList() {
        return resultList;
    }

    public void setResultList(Object resultList) {
        this.resultList = resultList;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public QimenApiResponse(String errorCode, boolean resultSuccess, String errorMessage) {
        this.errorCode = errorCode;
        this.resultSuccess = resultSuccess;
        this.errorMessage = errorMessage;
    }

    /**
     * 成功响应
     */
    public static QimenApiResponse successResponse(Object result) {
        QimenApiResponse response = new QimenApiResponse("invalid-request", Boolean.TRUE, "System error");
        response.setResultSuccess(Boolean.TRUE);
        if (result != null && result instanceof Collection) {
            response.setResultList(result);
        } else {
            response.setResult(result);
        }
        return response;
    }

    /**
     * 程序错误响应
     */
    public static QimenApiResponse errorResponse(String message) {
        QimenApiResponse error = new QimenApiResponse();
        error.errorCode = FAILED_CODE;
        error.resultSuccess = Boolean.FALSE;
        error.errorMessage = message;
        return error;
    }

    /**
     * 验签失败响应
     */
    public static QimenApiResponse signCheckFailResponse() {
        QimenApiResponse fail = new QimenApiResponse();
        fail.setErrorCode(SIGN_CHECK_FAILURE);
        fail.setResultErrorCode(SIGN_CHECK_FAILURE);
        fail.setResultSuccess(Boolean.FALSE);
        fail.setResultErrorDesc(ILLEGAL_REQUEST);
        fail.setErrorMessage(ILLEGAL_REQUEST);
        fail.setErrorDesc(ILLEGAL_REQUEST);
        fail.setIsSuccess(Boolean.FALSE);
        // 下面三个是自定义场景需要的返回码
        fail.setSubMessage(ILLEGAL_REQUEST);
        fail.setFlag(FAILURE);
        fail.setSubCode(SIGN_CHECK_FAILURE);
        JSONObject result = new JSONObject();
        result.put("error_desc", ILLEGAL_REQUEST);
        result.put("error_code", SIGN_CHECK_FAILURE);
        result.put("success", Boolean.FALSE);
        result.put("error_message", ILLEGAL_REQUEST);
        fail.setResult(result);
        return fail;
    }

    public JSONObject toJson() {
        JSONObject object = JSONUtils.writeToJSON(this);
        if (result != null && result instanceof JSONObject) {
            object.remove("result");
            JSONUtils.merge(object, (JSONObject)result);
        }
        return object;
    }

    @Override
    public String toString() {
        return JSONUtils.writeToJSON(this).toJSONString();
    }
}
