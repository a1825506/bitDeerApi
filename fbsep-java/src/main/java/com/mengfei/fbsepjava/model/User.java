package com.mengfei.fbsepjava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String userUuid;
    private String userName;
    private String passWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_uuid() {
        return userUuid;
    }

    public void setUser_uuid(String user_uuid) {
        this.userUuid = user_uuid;
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
    @Email(message = "邮箱格式不正确")
    private String email;
    private String voucher_uuid;//我的礼品券
    private String revenue_address;//收益地址

    private int remind_type;//提醒类型  -1：失效后提醒  1：失效前一天提醒  7：失效前七天提醒

    @Transient
    private int type;//标记是手机用户还是邮箱用户

    private String google_authentication;//google身份验证起


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Transient
    private int code;//验证码





    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userUuid='" + userUuid + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", voucher_uuid='" + voucher_uuid + '\'' +
                ", revenue_address='" + revenue_address + '\'' +
                ", google_authentication='" + google_authentication + '\'' +
                ", remind_type='" + remind_type + '\'' +



                '}';
    }

    public String getGoogle_authentication() {
        return google_authentication;
    }

    public void setGoogle_authentication(String google_authentication) {
        this.google_authentication = google_authentication;
    }

    public int getRemind_type() {
        return remind_type;
    }

    public void setRemind_type(int remind_type) {
        this.remind_type = remind_type;
    }
}
