package com.algebra.demodatasources.conf.db;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

/**
 * @author al
 * @date 2020/2/12 11:25
 * @description 从库配置类
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.algebra.demodatasources.mapper.slave"},sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {

    static final  String PACKAGES="com.algebra.demodatasources.mapper.slave";
    private  static final String MAPPER_LOCAL ="classpath*:mapper/slave/*.xml";

    @Autowired
    private SlaveDataBaseProperties slaveProp;

    @Bean(name = "slaveDruidDataSource")
    public DruidDataSource slaveDruidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(slaveProp.driverClassName);
        druidDataSource.setUrl(slaveProp.url);
        druidDataSource.setUsername(slaveProp.username);
        druidDataSource.setPassword(slaveProp.password);
        return druidDataSource;
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory() {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(slaveDruidDataSource());
        try {
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error("配置从库的SqlSessionFactory失败，error:{}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager slaveTransactionManager(){
        return new DataSourceTransactionManager(slaveDruidDataSource());
    }

}
