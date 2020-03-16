package com.mengfei.admApijava.pojo;

public enum ResponeCode {

    SUCCESS(200),FAIL(201);

    private int code;

    private ResponeCode(int code){

        this.code = code;

    }


}
