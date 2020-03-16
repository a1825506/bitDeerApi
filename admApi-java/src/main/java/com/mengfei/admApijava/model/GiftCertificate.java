package com.mengfei.admApijava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 礼券表
 */
@Entity
public class GiftCertificate {

    @Id
    @GeneratedValue
    private Long id;

    private String giftUuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiftUuid() {
        return giftUuid;
    }

    public void setGiftUuid(String giftUuid) {
        this.giftUuid = giftUuid;
    }

    public int getGift_type() {
        return gift_type;
    }

    public void setGift_type(int gift_type) {
        this.gift_type = gift_type;
    }

    public int getConsumption_amount() {
        return consumption_amount;
    }

    public void setConsumption_amount(int consumption_amount) {
        this.consumption_amount = consumption_amount;
    }

    public String getMiner_uuid() {
        return miner_uuid;
    }

    public void setMiner_uuid(String miner_uuid) {
        this.miner_uuid = miner_uuid;
    }

    public int getCycle_day() {
        return cycle_day;
    }

    public void setCycle_day(int cycle_day) {
        this.cycle_day = cycle_day;
    }

    public int getHashRate_value() {
        return hashRate_value;
    }

    public void setHashRate_value(int hashRate_value) {
        this.hashRate_value = hashRate_value;
    }

    public int getExtended_days() {
        return extended_days;
    }

    public void setExtended_days(int extended_days) {
        this.extended_days = extended_days;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    private int gift_type;//1:电费减免  2：挖矿天数延长

    /****使用条件*****/
    private int consumption_amount;//消费金额

    private String miner_uuid;//矿机型号

    private int cycle_day;//套餐天数

    private int hashRate_value;//算力值

    private int extended_days;

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    /****有效期*****/
    private String begin_date;
    private String end_date;

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    private int effective;//0:失效  1：有效


    @Override
    public String toString() {
        return "GiftCertificate{" +
                "id=" + id +
                ", giftUuid='" + giftUuid + '\'' +
                ", gift_type=" + gift_type +
                ", consumption_amount=" + consumption_amount +
                ", miner_uuid='" + miner_uuid + '\'' +
                ", cycle_day=" + cycle_day +
                ", hashRate_value=" + hashRate_value +
                ", extended_days=" + extended_days +
                ", begin_date='" + begin_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", effective=" + effective +
                '}';
    }
}
