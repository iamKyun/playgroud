package com.iamkyun.playground.starter.database;

import com.iamkyun.playground.starter.database.mybatis.ReadWriteSplittingPlugin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class DatabaseConfiguration {

    /**
     * 读写分离插件
     */
    @ConditionalOnProperty(prefix = "playground.database.read-write-splitting", name = "enabled", havingValue = "true")
    @Bean
    public ReadWriteSplittingPlugin readWriteSplittingPlugin() {
        return new ReadWriteSplittingPlugin();
    }

}
