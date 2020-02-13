package com.algebra.demodatasources.conf.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author al
 * @date 2020/2/12 11:22
 * @description 主库配置类
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.algebra.demodatasources.mapper.master"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    static final  String PACKAGES="com.algebra.demodatasources.mapper.master";
    private  static final String MAPPER_LOCAL ="classpath*:mapper/master/*.xml";

    @Autowired
    private MasterDataBaseProperties masterProp;

//    @Bean(name = "masterDruidDataSource")
//    @Primary
//    public DruidDataSource masterDruidDataSource(){
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setDriverClassName(masterProp.driverClassName);
//        druidDataSource.setUrl(masterProp.url);
//        druidDataSource.setUsername(masterProp.username);
//        druidDataSource.setPassword(masterProp.password);
//        return druidDataSource;
//    }

    @Bean(name = "masterDruidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    @Primary
    public DataSource masterDruidDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

//    @Bean(name = "masterSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDruidDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:mapper/master/*.xml"));
//        return sessionFactoryBean.getObject();
//    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDruidDataSource") DataSource masterDataSource) {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(masterDataSource);
        try {
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
            sessionFactoryBean.setTypeAliasesPackage("com.algebra.demodatasources.entity.domain");
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error("配置主库的SqlSessionFactory失败，error:{}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDruidDataSource") DataSource masterDataSource){
        return new DataSourceTransactionManager(masterDataSource);
    }

    @Bean(name = "pageHelper")
    public PageHelper pageHelper(){
        log.info("开始配置数据分页插件");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        //配置mysql数据库的方言
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
