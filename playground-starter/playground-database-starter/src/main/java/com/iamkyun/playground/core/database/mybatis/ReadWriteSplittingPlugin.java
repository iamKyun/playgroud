package com.iamkyun.playground.core.database.mybatis;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.ds.GroupDataSource;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.support.HealthCheckAdapter;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                                                                    RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                                                                    RowBounds.class, ResultHandler.class,
                                                                    CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Slf4j
public class ReadWriteSplittingPlugin implements Interceptor {

    public static final String WRITE = "write";
    public static final String READ = "read";

    @Autowired
    protected DataSource dynamicDataSource;

    @Autowired
    private DynamicDataSourceProperties properties;

    @Lazy
    @Autowired(required = false)
    private HealthCheckAdapter healthCheckAdapter;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        String pushedDataSource = null;
        try {
            String dataSource = getDataSource(ms);
            pushedDataSource = DynamicDataSourceContextHolder.push(dataSource);
            return invocation.proceed();
        } finally {
            if (pushedDataSource != null) {
                DynamicDataSourceContextHolder.poll();
            }
        }
    }

    /**
     * 获取动态数据源名称，重写注入 DbHealthIndicator 支持数据源健康状况判断选择
     *
     * @param mappedStatement mybatis MappedStatement
     * @return 获取真实的数据源名称
     */
    public String getDataSource(MappedStatement mappedStatement) {
        String currentDataSource =
                SqlCommandType.SELECT == mappedStatement.getSqlCommandType() ? READ : WRITE;
        String dataSource = null;
        if (properties.isHealth()) {
            DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) dynamicDataSource;
            // 当前数据源是从库
            if (READ.equalsIgnoreCase(currentDataSource)) {
                Map<String, GroupDataSource> currentGroupDataSources =
                        dynamicRoutingDataSource.getCurrentGroupDataSources();
                GroupDataSource groupDataSource = currentGroupDataSources.get(READ);
                String dsKey = groupDataSource.determineDsKey();
                boolean health = healthCheckAdapter.getHealth(dsKey);
                if (health) {
                    dataSource = dsKey;
                } else {
                    log.warn("从库无法连接, 请检查数据库配置, key: {}", dsKey);
                }
            }
            // 从库无法连接, 或者当前数据源需要操作主库
            if (dataSource == null) {
                // 当前数据源是主库
                Map<String, GroupDataSource> currentGroupDataSources =
                        dynamicRoutingDataSource.getCurrentGroupDataSources();
                GroupDataSource groupDataSource = currentGroupDataSources.get(WRITE);
                dataSource = groupDataSource.determineDsKey();
                boolean health = healthCheckAdapter.getHealth(dataSource);
                if (!health) {
                    log.warn("主库无法连接, 请检查数据库配置, key: {}", dataSource);
                }
            }
        } else {
            dataSource = currentDataSource;
        }
        return dataSource;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
