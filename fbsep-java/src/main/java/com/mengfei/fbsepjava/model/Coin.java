package com.mengfei.fbsepjava.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 数字货币实体类
 */
@Entity
public class Coin {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoinUuid() {
        return coinUuid;
    }

    public void setCoinUuid(String coinUuid) {
        this.coinUuid = coinUuid;
    }

    public String getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(String coin_id) {
        this.coin_id = coin_id;
    }

    public String getCoin_symbol() {
        return coin_symbol;
    }

    public void setCoin_symbol(String coin_symbol) {
        this.coin_symbol = coin_symbol;
    }

    public String getCoin_cn_name() {
        return coin_cn_name;
    }

    public void setCoin_cn_name(String coin_cn_name) {
        this.coin_cn_name = coin_cn_name;
    }

    public String getCoin_icon() {
        return coin_icon;
    }

    public void setCoin_icon(String coin_icon) {
        this.coin_icon = coin_icon;
    }

    public String getCoin_introduction() {
        return coin_introduction;
    }

    public void setCoin_introduction(String coin_introduction) {
        this.coin_introduction = coin_introduction;
    }


    public String getStatic_return_rate() {
        return static_return_rate;
    }

    public void setStatic_return_rate(String static_return_rate) {
        this.static_return_rate = static_return_rate;
    }

    public String getMiner_uuid() {
        return miner_uuid;
    }

    public void setMiner_uuid(String miner_uuid) {
        this.miner_uuid = miner_uuid;
    }


    @Column(nullable = false, unique = true)
    private String coinUuid;
    private String coin_id;
    private String coin_symbol;
    private String coin_cn_name;
    private String coin_icon;
    private String coin_introduction;
    private String miner_uuid;

    @Transient
    private String miner_nanme;

    private String static_return_rate;




    @Override
    public String toString() {
        return "Coin{" +
                "id=" + id +
                ", coinUuid='" + coinUuid + '\'' +
                ", coin_id='" + coin_id + '\'' +
                ", coin_symbol='" + coin_symbol + '\'' +
                ", coin_cn_name='" + coin_cn_name + '\'' +
                ", coin_icon='" + coin_icon + '\'' +
                ", coin_introduction='" + coin_introduction + '\'' +
                ", Static_return_rate='" + static_return_rate + '\'' +
                ", miner_nanme='" + miner_nanme + '\'' +

                '}';
    }


    @Transient
    public String getMiner_nanme() {
        return miner_nanme;
    }

    public void setMiner_nanme(String miner_nanme) {
        this.miner_nanme = miner_nanme;
    }
}
