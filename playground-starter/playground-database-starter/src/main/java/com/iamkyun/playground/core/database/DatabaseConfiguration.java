package com.iamkyun.playground.core.database;

import com.iamkyun.playground.core.database.mybatis.ReadWriteSplittingPlugin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
