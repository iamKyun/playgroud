package com.iamkyun.playground.core.database.autoconfigure;

import com.iamkyun.playground.core.database.mybatis.ReadWriteSplittingPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfiguration {
    @Bean
    public ReadWriteSplittingPlugin readWriteSplittingPlugin() {
        return new ReadWriteSplittingPlugin();
    }
}
