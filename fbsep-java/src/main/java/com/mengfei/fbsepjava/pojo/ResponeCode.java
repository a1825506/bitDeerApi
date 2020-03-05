package com.mengfei.fbsepjava.pojo;

public enum ResponeCode {

    SUCCESS(200),FAIL(201);

    private int code;

    private ResponeCode(int code){

        this.code = code;

    }


}
