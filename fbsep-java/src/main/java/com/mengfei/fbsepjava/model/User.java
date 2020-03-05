package com.mengfei.fbsepjava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String user_uuid;
    private String userName;
    private String passWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVoucher_uuid() {
        return voucher_uuid;
    }

    public void setVoucher_uuid(String voucher_uuid) {
        this.voucher_uuid = voucher_uuid;
    }

    public String getRevenue_address() {
        return revenue_address;
    }

    public void setRevenue_address(String revenue_address) {
        this.revenue_address = revenue_address;
    }

    private String phoneNum;
    private String email;
    private String voucher_uuid;//我的礼品券
    private String revenue_address;//收益地址


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_uuid='" + user_uuid + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", voucher_uuid='" + voucher_uuid + '\'' +
                ", revenue_address='" + revenue_address + '\'' +
                '}';
    }
}
