package com.mengfei.admApijava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 收益地址数据表
 */

@Entity
public class RevenueAddress {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String userUuid) {
        this.user_uuid = userUuid;
    }


    public String getCoin_symbol() {
        return coin_symbol;
    }

    public void setCoin_symbol(String coin_symbol) {
        this.coin_symbol = coin_symbol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String user_uuid;


    private String coin_symbol;

    private String address;

    private String support_pool;

    private String info;


    @Override
    public String toString() {
        return "RevenueAddress{" +
                "id=" + id +
                ", user_uuid='" + user_uuid + '\'' +
                ", coin_symbol='" + coin_symbol + '\'' +
                ", address='" + address + '\'' +
                ", support_pool='" + support_pool + '\'' +
                ", info='" + info + '\'' +



                '}';
    }

    public String getSupport_pool() {
        return support_pool;
    }

    public void setSupport_pool(String support_pool) {
        this.support_pool = support_pool;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
