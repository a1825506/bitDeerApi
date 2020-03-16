package com.mengfei.admApijava.model;


import java.util.List;

public class SalePackage {

    public Miner getMiner() {
        return miner;
    }

    public void setMiner(Miner miner) {
        this.miner = miner;
    }



    private Miner miner;

    private List<HashRateSale> hashRateSales;

    public List<HashRateSale> getHashRateSales() {
        return hashRateSales;
    }

    public void setHashRateSales(List<HashRateSale> hashRateSales) {
        this.hashRateSales = hashRateSales;
    }
}
