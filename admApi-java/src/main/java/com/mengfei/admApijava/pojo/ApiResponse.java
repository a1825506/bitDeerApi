package com.mengfei.admApijava.pojo;

/**
 *  统一的JSON格式数据响应类
 */
public class ApiResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public ApiResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //getter setter ...

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
