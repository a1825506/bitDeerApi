package com.mengfei.admApijava.model;

import com.mengfei.admApijava.model.retruns.PageInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

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
    private String hashRate_uuid;//商品
    private int orderStatus;//订单状态 0：系统异常  1：已支付  2：代支付  3：支出中  4：分笔支付中  5：代下发  6：已完成   7：已取消
    private String userUuid;//订单所属人
    private String coin_uuid;//数字货币id
    private String miner_model;//矿机机型
    private String hashRate_value;//套餐算力
    private String cycle_day;//套餐周期
    private String extended_days;//延长天数
    private String miningPool_uuid;//矿池
    private String payment_method;//收款方式  0 个人钱包  1 Matrixport钱包
    private String computing_power;//订单算力费
    private String electricity_bill;//订单电费

    private String hashRate_name;//套餐名


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Transient
    private String userName;//订单所属用户

    @Transient
    private String phoneNum;//订单所属手机号

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    @Transient
    private String coin;//数字货币


    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    private int order_type;// 0：算力订单  1：电费订单  100：全部订单

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

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String user_uuid) {
        this.userUuid = user_uuid;
    }

    public String getCoin_uuid() {
        return coin_uuid;
    }

    public void setCoin_uuid(String coin_id) {
        this.coin_uuid = coin_id;
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

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", order_uuid='" + order_uuid + '\'' +
                ", order_date='" + order_date + '\'' +
                ", order_amount='" + order_amount + '\'' +
                ", hashRate_uuid='" + hashRate_uuid + '\'' +
                ", orderStatus=" + orderStatus +
                ", userUuid='" + userUuid + '\'' +
                ", coin_uuid='" + coin_uuid + '\'' +
                ", miner_model='" + miner_model + '\'' +
                ", hashRate_value='" + hashRate_value + '\'' +
                ", cycle_day='" + cycle_day + '\'' +
                ", extended_days='" + extended_days + '\'' +
                ", miningPool_uuid='" + miningPool_uuid + '\'' +
                ", payment_method='" + payment_method + '\'' +
                ", computing_power='" + computing_power + '\'' +
                ", electricity_bill='" + electricity_bill + '\'' +
                ", order_type='" + order_type + '\'' +
                ", hashRate_name='" + hashRate_name + '\'' +



                '}';
    }

    public String getHashRate_name() {
        return hashRate_name;
    }

    public void setHashRate_name(String hashRate_name) {
        this.hashRate_name = hashRate_name;
    }


}
