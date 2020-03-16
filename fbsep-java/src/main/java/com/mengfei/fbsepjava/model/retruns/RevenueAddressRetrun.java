package com.mengfei.fbsepjava.model.retruns;

import com.mengfei.fbsepjava.model.RevenueAddress;

import java.util.List;

public class RevenueAddressRetrun {


    private String support_pool;

    private String coin_symbol;

    private List<RevenueAddress> revenueAddresses;



    public List<RevenueAddress> getRevenueAddresses() {
        return revenueAddresses;
    }

    public void setRevenueAddresses(List<RevenueAddress> revenueAddresses) {
        this.revenueAddresses = revenueAddresses;
    }

    public String getSupport_pool() {
        return support_pool;
    }

    public void setSupport_pool(String support_pool) {
        this.support_pool = support_pool;
    }

    public String getCoin_symbol() {
        return coin_symbol;
    }

    public void setCoin_symbol(String coin_symbol) {
        this.coin_symbol = coin_symbol;
    }
}
