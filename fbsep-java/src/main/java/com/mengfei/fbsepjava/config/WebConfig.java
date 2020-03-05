package com.mengfei.fbsepjava.config;

import com.mengfei.fbsepjava.filter.RepeatedlyReadFilter;
import com.mengfei.fbsepjava.interceptor.RepeatedlyReadInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new RepeatedlyReadInterceptor())
                .excludePathPatterns("/login/**")//放行登录接口
                .excludePathPatterns("/sys/attach/preview/**")//放行文件预览接口
                .excludePathPatterns("/upload/**")//放行文件访问静态资源url
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**")//放行swagger接口文档地址
                .addPathPatterns("/**/**");

    }

    @Bean
    public FilterRegistrationBean repeatedlyReadFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        RepeatedlyReadFilter repeatedlyReadFilter = new RepeatedlyReadFilter();
        registration.setFilter(repeatedlyReadFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
