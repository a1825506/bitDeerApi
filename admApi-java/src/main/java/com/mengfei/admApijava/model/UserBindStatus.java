package com.mengfei.admApijava.model;

/**
 * 用户状态绑定 实体
 */
public class UserBindStatus {

    private String passWord;//密码绑定状态

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

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    private String phoneNum;//手机绑定状态

    private String email;//邮箱绑定状态

    private String google;//谷歌身份验证器
}
