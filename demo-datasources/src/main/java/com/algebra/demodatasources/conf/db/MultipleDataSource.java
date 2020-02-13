package com.algebra.demodatasources.conf.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/2/12 16:50
 * @description 自定义多数据源配置类
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    public MultipleDataSource(){
    }

    public MultipleDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources){
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
    }

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
        return getDatasource();
    }
}
