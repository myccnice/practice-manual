package com.myccnice.practice.manual.qimen.vo;

import com.taobao.api.TaobaoObject;
import com.taobao.api.internal.mapping.ApiField;

/**
 * 接收声明发货接口参数的vo类
 *
 * create in 2018年3月24日
 * @author wangpeng
 */
public class SellerShipmentForTopDto extends TaobaoObject {

    private static final long serialVersionUID = -532643210442736065L;

    /** 
     * memo
     */
    private String description;

    /** 
     * 国际运单号
     */
    @ApiField("logistics_no")
    private String logisticsNo;

    /** 
     * 交易订单号
     */
    @ApiField("out_ref")
    private String outRef;

    /** 
     * 声明发货类型，all表示全部发货，part表示部分声明发货。
     */
    @ApiField("send_type")
    private String sendType;

    /** 
     * 物流服务名称
     */
    @ApiField("service_name")
    private String serviceName;

    /** 
     * 追踪网址
     */
    @ApiField("tracking_website")
    private String trackingWebsite;

    /** 
     * 新的运单号
     */
    private String newLogisticsNo;

    /** 
     * 新的发货物流服务
     */
    private String newServiceName;

    /** 
     * 旧的运单号
     */
    private String oldLogisticsNo;

    /** 
     * 用户需要修改的的老的发货物流服务
     */
    private String oldServiceName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getOutRef() {
        return outRef;
    }

    public void setOutRef(String outRef) {
        this.outRef = outRef;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTrackingWebsite() {
        return trackingWebsite;
    }

    public void setTrackingWebsite(String trackingWebsite) {
        this.trackingWebsite = trackingWebsite;
    }

    public String getNewLogisticsNo() {
        return newLogisticsNo;
    }

    public void setNewLogisticsNo(String newLogisticsNo) {
        this.newLogisticsNo = newLogisticsNo;
    }

    public String getNewServiceName() {
        return newServiceName;
    }

    public void setNewServiceName(String newServiceName) {
        this.newServiceName = newServiceName;
    }

    public String getOldLogisticsNo() {
        return oldLogisticsNo;
    }

    public void setOldLogisticsNo(String oldLogisticsNo) {
        this.oldLogisticsNo = oldLogisticsNo;
    }

    public String getOldServiceName() {
        return oldServiceName;
    }

    public void setOldServiceName(String oldServiceName) {
        this.oldServiceName = oldServiceName;
    }

}
