package com.mengfei.admApijava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan  //注册过滤器注解
public class AdmApiJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmApiJavaApplication.class, args);
    }

}
