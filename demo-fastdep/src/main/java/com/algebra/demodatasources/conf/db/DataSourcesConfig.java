package com.algebra.demodatasources.conf.db;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/2/13 8:50
 * @description
 */
@Configuration
public class DataSourcesConfig {

    @Bean("dynamicDataSource")
    public DynamicDataSource getDataSources(@Qualifier("masterDruidDataSource") DataSource masterDataSource,
                                            @Qualifier("slaveDruidDataSource") DataSource slaveDataSource){
        Map<String, DataSource> dataSources = new HashMap<>();
        dataSources.put("masterDruidDataSource",masterDataSource);
        dataSources.put("slaveDruidDataSource",slaveDataSource);
//        MultipleDataSource.setDataSource("slaveDruidDataSource");
        return new DynamicDataSource(masterDataSource,dataSources);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DynamicDataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // Here is very important, if don't config this, will can't switch datasource
        // put all datasource into SqlSessionFactoryBean, then will autoconfig SqlSessionFactory
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

}
