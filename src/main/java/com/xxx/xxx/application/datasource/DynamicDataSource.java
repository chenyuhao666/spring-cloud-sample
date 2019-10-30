package com.xxx.xxx.application.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源配置
 *
 * @author yan.li
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> dataSourceNameHolder = new ThreadLocal<>();

    /**
     * 构造器
     *
     * @param defaultTargetDataSource
     * @param targetDataSources
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceName();
    }

    /**
     * 设置对应线程数据源名称
     *
     * @param dataSourceName
     */
    public static void setDataSourceName(String dataSourceName) {
        dataSourceNameHolder.set(dataSourceName);
    }

    /**
     * 获取对应线程的数据源名称
     *
     * @return
     */
    public static String getDataSourceName() {
        return dataSourceNameHolder.get();
    }

    /**
     * 清理对应线程数据源名称
     */
    public static void clearDataSourceName() {
        dataSourceNameHolder.remove();
    }
}
