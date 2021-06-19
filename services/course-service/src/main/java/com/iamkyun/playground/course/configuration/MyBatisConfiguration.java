package com.iamkyun.playground.course.configuration;

import com.iamkyun.playground.course.mybatis.ReadWriteSplittingPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfiguration {
    @Bean
    public ReadWriteSplittingPlugin readWriteSplittingPlugin() {
        return new ReadWriteSplittingPlugin();
    }
}
