package com.mengfei.admApijava.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 添加此注解可以提供用户Token的安全检查服务
 */
@Target(ElementType.METHOD)//作用于方法级别
@Retention(RetentionPolicy.RUNTIME)
public @interface UserTokenCheck {
}
