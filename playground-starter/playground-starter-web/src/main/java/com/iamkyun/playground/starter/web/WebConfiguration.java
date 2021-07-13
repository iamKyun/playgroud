package com.iamkyun.playground.starter.web;

import com.iamkyun.playground.starter.web.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class WebConfiguration {
    @Bean
    public FilterRegistrationBean<AuthFilter> registerAuthFilter() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        registration.setOrder(1);
        return registration;
    }
}
