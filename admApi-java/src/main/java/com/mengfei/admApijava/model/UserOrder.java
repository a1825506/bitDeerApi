package com.mengfei.admApijava.model;

/**
 * 返回用户订单 实体类
 */
public class UserOrder {

    private String order_uuid;//系统内的uuid标识

    public String getOrder_uuid() {
        return order_uuid;
    }

    public void setOrder_uuid(String order_uuid) {
        this.order_uuid = order_uuid;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getHashRate() {
        return hashRate;
    }

    public void setHashRate(String hashRate_uuid) {
        this.hashRate = hashRate_uuid;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    private String order_date;//订单日期
    private String order_amount;//订单金额
    private String hashRate;//商品
    private int order_status;//订单状态 0：系统异常  1：已支付  2：代支付  3：支出中  4：分笔支付中  5：代下发  6：已完成   7：已取消

}
