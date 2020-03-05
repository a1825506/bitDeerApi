package com.mengfei.fbsepjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan  //注册过滤器注解
public class FbsepJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FbsepJavaApplication.class, args);
    }

}
