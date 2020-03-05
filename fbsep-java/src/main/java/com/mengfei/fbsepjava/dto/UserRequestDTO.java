package com.mengfei.fbsepjava.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 用于用户请求的数据传输对象
 */
public class UserRequestDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotNull
    @Size(min = 5,max = 10,message = "密码必须在5-10位之间")
    private String pwd;

    @Range(min = 0,max = 120,message = "年龄不能为负数并且不能超过120岁")
    private Integer age;

    @Pattern(regexp = "^([0-9A-Za-z\\-_\\.]+)@([0-9a-z]+\\.[a-z]{2,3}(\\.[a-z]{2})?)$",message = "需要输入正确格式的邮箱")
    private String email;

    //getter setter toString() ...

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
