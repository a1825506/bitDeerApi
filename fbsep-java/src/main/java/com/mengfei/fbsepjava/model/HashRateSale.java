package com.mengfei.fbsepjava.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 在售算力表
 *
 */

@Entity
public class HashRateSale {
    @Id
    @GeneratedValue
    private Long id;
    private String hashRate_uuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashRate_uuid() {
        return hashRate_uuid;
    }

    public void setHashRate_uuid(String hashRate_uuid) {
        this.hashRate_uuid = hashRate_uuid;
    }


    public String getCycle_day() {
        return cycle_day;
    }

    public void setCycle_day(String cycle_day) {
        this.cycle_day = cycle_day;
    }

    public String getHashRate_value() {
        return hashRate_value;
    }

    public void setHashRate_value(String hashRate_value) {
        this.hashRate_value = hashRate_value;
    }

    public String getHashRate_price() {
        return hashRate_price;
    }

    public void setHashRate_price(String hashRate_price) {
        this.hashRate_price = hashRate_price;
    }

    public String getHashRate_Total_count() {
        return hashRate_Total_count;
    }

    public void setHashRate_Total_count(String hashRate_Total_count) {
        this.hashRate_Total_count = hashRate_Total_count;
    }

    public String getHashRate_sale_count() {
        return hashRate_sale_count;
    }

    public void setHashRate_sale_count(String hashRate_sale_count) {
        this.hashRate_sale_count = hashRate_sale_count;
    }

    public String getHashRate_Remaining_count() {
        return hashRate_Remaining_count;
    }

    public void setHashRate_Remaining_count(String hashRate_Remaining_count) {
        this.hashRate_Remaining_count = hashRate_Remaining_count;
    }


    @ApiModelProperty(value = "", name = "hashRate_value", example = "算力值", required = false)
    private String hashRate_value;//算力值100
    @ApiModelProperty(value = "", name = "hashRate_price", example = "算力售价 单位  T/天", required = false)
    private String hashRate_price;//算力售价/天
    @ApiModelProperty(value = "", name = "hashRate_Total_count", example = "该算力总量", required = false)
    private String hashRate_Total_count;//该算力总量
    @ApiModelProperty(value = "", name = "hashRate_sale_count", example = "该算力已售数量", required = false)
    private String hashRate_sale_count;//该算力已售数量
    @ApiModelProperty(value = "", name = "hashRate_Remaining_count", example = "该算力剩余数量", required = false)
    private String hashRate_Remaining_count;//该算力剩余数量



    @Transient
    @ApiModelProperty(value = "", name = "minerName", example = "所属矿机系列名称（临时属性不写入数据库", required = false)
    private String minerName;//所属矿机系列名称（临时属性不写入数据库）

    public String getMinerUuid() {
        return minerUuid;
    }

    public void setMinerUuid(String minerUuid) {
        this.minerUuid = minerUuid;
    }

    public String getCoinUuid() {
        return coinUuid;
    }

    public void setCoinUuid(String coinUuid) {
        this.coinUuid = coinUuid;
    }

    @Column(name="miner_uuid")
    @ApiModelProperty(value = "", name = "minerUuid", example = "所属矿机系列", required = false)
    private String minerUuid;//所属矿机系列
    @Column(name="coin_uuid")
    @ApiModelProperty(value = "", name = "coinUuid", example = "所属数字货币", required = false)
    private String coinUuid;//所属数字货币
    @ApiModelProperty(value = "", name = "cycle_day", example = "套餐天数", required = false)
    private String cycle_day;//套餐天数
    @ApiModelProperty(value = "", name = "total_sale_price", example = "total_sale_price", required = false)
    private String total_sale_price;//套餐总价

    public String getTotal_sale_price() {
        return total_sale_price;
    }

    public void setTotal_sale_price(String total_sale_price) {
        this.total_sale_price = total_sale_price;
    }



    @Override
    public String toString() {
        return "HashRateSale{" +
                "id=" + id +
                ", hashRate_uuid='" + hashRate_uuid + '\'' +
                ", hashRate_value='" + hashRate_value + '\'' +
                ", hashRate_price='" + hashRate_price + '\'' +
                ", hashRate_Total_count='" + hashRate_Total_count + '\'' +
                ", hashRate_sale_count='" + hashRate_sale_count + '\'' +
                ", hashRate_Remaining_count='" + hashRate_Remaining_count + '\'' +
                ", minerUuid='" + minerUuid + '\'' +
                ", minerName='" + minerName + '\'' +
                ", coinUuid='" + coinUuid + '\'' +
                ", cycle_day='" + cycle_day + '\'' +
                ", total_sale_price='" + total_sale_price + '\'' +
                '}';
    }

    @Transient
    public String getMinerName() {
        return minerName;
    }

    public void setMinerName(String minerName) {
        this.minerName = minerName;
    }
}
