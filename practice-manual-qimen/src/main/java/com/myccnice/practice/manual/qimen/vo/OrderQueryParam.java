package com.myccnice.practice.manual.qimen.vo;

/**
 * 订单查询参数
 *
 * create in 2018年3月15日
 * @author wangpeng
 */
public class OrderQueryParam {

    private String id;

    /**
     * 订单创建时间结束值，格式: MM/dd/yyyy HH:mm:ss,如10/08/2013 00:00:00 倘若时间维度未精确到时分秒，故该时间条件筛选不许生效。
     */
    private String create_date_end;

    /**
     * 订单创建时间起始值，格式: MM/dd/yyyy HH:mm:ss,如10/08/2013 00:00:00 倘若时间维度未精确到时分秒，故该时间条件筛选不许生效。
     */
    private String create_date_start;

    /**
     * 查询多个订单状态下的订单信息，具体订单状态见order_status描述。如["PLACE_ORDER_SUCCESS","WAIT_SELLER_SEND_GOODS"]，最大列表长度：20。
     */
    private String[] order_status_list;

    /**
     * 当前页码
     */
    private Integer page;

    /**
     * 每页个数，最大50
     */
    private Integer page_size;

    /**
     * 订单状态
     */
    private String order_status;

    /**
     * 订单修改时间起始值，格式: MM/dd/yyyy HH:mm:ss,如10/08/2013 00:00:00
     */
    private String modified_date_start;

    /**
     * 订单修改时间起始值，格式: MM/dd/yyyy HH:mm:ss,如10/08/2013 00:00:00
     */
    private String modified_date_end;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreate_date_end() {
        return create_date_end;
    }

    public void setCreate_date_end(String create_date_end) {
        this.create_date_end = create_date_end;
    }

    public String getCreate_date_start() {
        return create_date_start;
    }

    public void setCreate_date_start(String create_date_start) {
        this.create_date_start = create_date_start;
    }

    public String[] getOrder_status_list() {
        return order_status_list;
    }

    public void setOrder_status_list(String[] order_status_list) {
        this.order_status_list = order_status_list;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getModified_date_start() {
        return modified_date_start;
    }

    public void setModified_date_start(String modified_date_start) {
        this.modified_date_start = modified_date_start;
    }

    public String getModified_date_end() {
        return modified_date_end;
    }

    public void setModified_date_end(String modified_date_end) {
        this.modified_date_end = modified_date_end;
    }

}
