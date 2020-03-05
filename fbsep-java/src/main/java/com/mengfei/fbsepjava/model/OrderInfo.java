package com.mengfei.fbsepjava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 订单实体类
 */
@Entity
public class OrderInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String order_uuid;//系统内的uuid标识
    private String order_date;//订单日期
    private String order_amount;//订单金额

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getHashRate_uuid() {
        return hashRate_uuid;
    }

    public void setHashRate_uuid(String hashRate_uuid) {
        this.hashRate_uuid = hashRate_uuid;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(String coin_id) {
        this.coin_id = coin_id;
    }

    public String getMiner_model() {
        return miner_model;
    }

    public void setMiner_model(String miner_model) {
        this.miner_model = miner_model;
    }

    public String getHashRate_value() {
        return hashRate_value;
    }

    public void setHashRate_value(String hashRate_value) {
        this.hashRate_value = hashRate_value;
    }

    public String getCycle_day() {
        return cycle_day;
    }

    public void setCycle_day(String cycle_day) {
        this.cycle_day = cycle_day;
    }

    public String getExtended_days() {
        return extended_days;
    }

    public void setExtended_days(String extended_days) {
        this.extended_days = extended_days;
    }

    public String getMiningPool_uuid() {
        return miningPool_uuid;
    }

    public void setMiningPool_uuid(String miningPool_uuid) {
        this.miningPool_uuid = miningPool_uuid;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getComputing_power() {
        return computing_power;
    }

    public void setComputing_power(String computing_power) {
        this.computing_power = computing_power;
    }

    public String getElectricity_bill() {
        return electricity_bill;
    }

    public void setElectricity_bill(String electricity_bill) {
        this.electricity_bill = electricity_bill;
    }

    private String hashRate_uuid;//商品
    private int order_status;//订单状态 0：系统异常  1：已支付  2：代支付  3：支出中  4：分笔支付中  5：代下发  6：已完成   7：已取消
    private String user_uuid;//订单所属人
    private String coin_id;//数字货币id
    private String miner_model;//矿机机型
    private String hashRate_value;//套餐算力
    private String cycle_day;//套餐周期
    private String extended_days;//延长天数
    private String miningPool_uuid;//矿池
    private String payment_method;//收款方式
    private String computing_power;//订单算力费
    private String electricity_bill;//订单电费

}
