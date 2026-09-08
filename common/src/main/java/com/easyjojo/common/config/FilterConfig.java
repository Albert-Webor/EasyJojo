package com.easyjojo.common.config;

import com.easyjojo.common.filters.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<RequestFilter> myFilterBean() {
        FilterRegistrationBean<RequestFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new RequestFilter());
        bean.addUrlPatterns("/api/*"); // 指定拦截路径
        bean.setOrder(1);              // 顺序
        return bean;
    }
}
