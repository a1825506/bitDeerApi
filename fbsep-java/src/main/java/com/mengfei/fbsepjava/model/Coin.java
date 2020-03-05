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

    public String getCoin_uuid() {
        return coin_uuid;
    }

    public void setCoin_uuid(String coin_uuid) {
        this.coin_uuid = coin_uuid;
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
    @ApiModelProperty(value = "币的唯一标识,系统自动生成", name = "coin_uuid", example = "币的唯一标识,系统自动生成", required = false)
    private String coin_uuid;
    @ApiModelProperty(value = "数字货币id", name = "coin_id", example = "数字货币id", required = true)
    private String coin_id;
    @ApiModelProperty(value = "数字货币Symbol", name = "coin_symbol", example = "数字货币Symbol", required = true)
    private String coin_symbol;
    @ApiModelProperty(value = "数字货币中文名", name = "coin_cn_name", example = "数字货币中文名", required = false)
    private String coin_cn_name;
    @ApiModelProperty(value = "数字货币图标URL", name = "coin_icon", example = "数字货币图标URL", required = false)
    private String coin_icon;
    @ApiModelProperty(value = "数字货币介绍", name = "coin_icon", example = "数字货币介绍", required = false)
    private String coin_introduction;
    @ApiModelProperty(value = "数字货币所属矿机的唯一标识", name = "coin_icon", example = "数字货币所属矿机的唯一标识", required = true)
    private String miner_uuid;

    @Transient
    @ApiModelProperty(value = "SHA256矿机", name = "coin_icon", example = "数字货币所属矿机名称", required = true)
    private String miner_nanme;

    @ApiModelProperty(value = "89.23%", name = "Static_return_rate", example = "静态收益率", required = true)
    private String static_return_rate;




    @Override
    public String toString() {
        return "Coin{" +
                "id=" + id +
                ", coin_uuid='" + coin_uuid + '\'' +
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
