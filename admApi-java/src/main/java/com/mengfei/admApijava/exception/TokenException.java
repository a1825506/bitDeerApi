package com.mengfei.admApijava.exception;

/**
 * 自定义TokenException类
 */
public class TokenException extends RuntimeException {

    public TokenException(){super();}

    public TokenException(String message){
        super(message);
    }
}
