package com.mengfei.admApijava.model;

import java.util.List;

/**
 * 我的算力模块 数据数据实体类
 */
public class UserHashRate {

    private String coin_name;

    public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getCoin_icon() {
        return coin_icon;
    }

    public void setCoin_icon(String coin_icon) {
        this.coin_icon = coin_icon;
    }

    public int getPackagesNum() {
        return packagesNum;
    }

    public void setPackagesNum(int packagesNum) {
        this.packagesNum = packagesNum;
    }

    public String getTotal_hashRate() {
        return total_hashRate;
    }

    public void setTotal_hashRate(String total_hashRate) {
        this.total_hashRate = total_hashRate;
    }

    public String getTotal_output() {
        return total_output;
    }

    public void setTotal_output(String total_output) {
        this.total_output = total_output;
    }

    public List<HashList> getHashLists() {
        return hashLists;
    }

    public void setHashLists(List<HashList> hashLists) {
        this.hashLists = hashLists;
    }

    private String coin_icon;

    private int packagesNum;//套餐数量

    private String total_hashRate;//总算力

    private String total_output;//总产出

    private List<HashList> hashLists;//算力列表






}
