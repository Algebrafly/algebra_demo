package com.algebra.demodatasources.conf.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author al
 * @date 2020/2/12 16:50
 * @description 自定义多数据源配置类
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSource 数据源名称
     */
    public static void setDataSource(String dataSource){
        dataSourceHolder.set(dataSource);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDatasource() {
        return dataSourceHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource(){
        dataSourceHolder.remove();
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceHolder.get();
    }
}
