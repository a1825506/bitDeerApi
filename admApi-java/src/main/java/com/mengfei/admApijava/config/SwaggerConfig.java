package com.mengfei.admApijava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于swagger生成在线文档的配置类
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描的swagger接口包路径
                .apis(RequestHandlerSelectors.basePackage("com.mengfei.fbsepjava.controller"))
                .paths(PathSelectors.any())
                .build();
                //.globalOperationParameters(this.setParameter());//不需要添加全局参数时这一行可以删掉
    }

    //通过参数构造器为swagger添加对header参数的支持，如果不需要的话可以删掉
    private List<Parameter> setParameter(){
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("userToken").description("用户的Token信息")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false) //header中的userToken参数现在设置的是非必填，传空也可以
                .build();
        pars.add(ticketPar.build());
        return pars;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springboot利用swagger构建api文档")
                .description("简单优雅的restful风格 http://blog.csdn.net/")
                .termsOfServiceUrl("http://blog.csdn.net/")
                .version("1.0")
                .build();
    }

}
