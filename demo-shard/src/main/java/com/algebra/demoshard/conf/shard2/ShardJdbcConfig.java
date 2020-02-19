package com.algebra.demoshard.conf.shard2;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库分库分表配置
 */
//@Configuration
public class ShardJdbcConfig {

    @Value("${spring.datasource.dataOne.druid.url}")
    private String dbUrl1;
    @Value("${spring.datasource.dataOne.druid.username}")
    private String username1;
    @Value("${spring.datasource.dataOne.druid.password}")
    private String password1;
    @Value("${spring.datasource.dataOne.druid.driverClassName}")
    private String driverClassName1;
    @Value("${spring.datasource.dataOne.druid.initial-size}")
    private int initialSize1;
    @Value("${spring.datasource.dataOne.druid.max-active}")
    private int maxActive1;
    @Value("${spring.datasource.dataOne.druid.min-idle}")
    private int minIdle1;
    @Value("${spring.datasource.dataOne.druid.max-wait}")
    private int maxWait1;
    @Value("${spring.datasource.dataOne.druid.pool-prepared-statements}")
    private boolean poolPreparedStatements1;
    @Value("${spring.datasource.dataOne.druid.max-pool-prepared-statement-per-connection-size}")
    private int maxPoolPreparedStatementPerConnectionSize1;
    @Value("${spring.datasource.dataOne.druid.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis1;
    @Value("${spring.datasource.dataOne.druid.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis1;
    @Value("${spring.datasource.dataOne.druid.max-evictable-idle-time-millis}")
    private int maxEvictableIdleTimeMillis1;
    @Value("${spring.datasource.dataOne.druid.validation-query}")
    private String validationQuery1;
    @Value("${spring.datasource.dataOne.druid.test-while-idle}")
    private boolean testWhileIdle1;
    @Value("${spring.datasource.dataOne.druid.test-on-borrow}")
    private boolean testOnBorrow1;
    @Value("${spring.datasource.dataOne.druid.test-on-return}")
    private boolean testOnReturn1;
    @Value("{spring.datasource.dataOne.druid.connection-properties}")
    private String connectionProperties1;

    @Bean
    public DruidDataSource dataOneSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl1);
        datasource.setUsername(username1);
        datasource.setPassword(password1);
        datasource.setDriverClassName(driverClassName1);
        datasource.setInitialSize(initialSize1);
        datasource.setMinIdle(minIdle1);
        datasource.setMaxActive(maxActive1);
        datasource.setMaxWait(maxWait1);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis1);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis1);
        datasource.setMaxEvictableIdleTimeMillis(minEvictableIdleTimeMillis1);
        datasource.setValidationQuery(validationQuery1);
        datasource.setTestWhileIdle(testWhileIdle1);
        datasource.setTestOnBorrow(testOnBorrow1);
        datasource.setTestOnReturn(testOnReturn1);
        datasource.setPoolPreparedStatements(poolPreparedStatements1);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize1);
        datasource.setConnectionProperties(connectionProperties1);
        return datasource;
    }

    @Value("${spring.datasource.dataTwo.druid.url}")
    private String dbUrl2;
    @Value("${spring.datasource.dataTwo.druid.username}")
    private String username2;
    @Value("${spring.datasource.dataTwo.druid.password}")
    private String password2;
    @Value("${spring.datasource.dataTwo.druid.driverClassName}")
    private String driverClassName2;
    @Value("${spring.datasource.dataTwo.druid.initial-size}")
    private int initialSize2;
    @Value("${spring.datasource.dataTwo.druid.max-active}")
    private int maxActive2;
    @Value("${spring.datasource.dataTwo.druid.min-idle}")
    private int minIdle2;
    @Value("${spring.datasource.dataTwo.druid.max-wait}")
    private int maxWait2;
    @Value("${spring.datasource.dataTwo.druid.pool-prepared-statements}")
    private boolean poolPreparedStatements2;
    @Value("${spring.datasource.dataTwo.druid.max-pool-prepared-statement-per-connection-size}")
    private int maxPoolPreparedStatementPerConnectionSize2;
    @Value("${spring.datasource.dataTwo.druid.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis2;
    @Value("${spring.datasource.dataTwo.druid.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis2;
    @Value("${spring.datasource.dataTwo.druid.max-evictable-idle-time-millis}")
    private int maxEvictableIdleTimeMillis2;
    @Value("${spring.datasource.dataTwo.druid.validation-query}")
    private String validationQuery2;
    @Value("${spring.datasource.dataTwo.druid.test-while-idle}")
    private boolean testWhileIdle2;
    @Value("${spring.datasource.dataTwo.druid.test-on-borrow}")
    private boolean testOnBorrow2;
    @Value("${spring.datasource.dataTwo.druid.test-on-return}")
    private boolean testOnReturn2;
    @Value("{spring.datasource.dataTwo.druid.connection-properties}")
    private String connectionProperties2;

    @Bean
    public DruidDataSource dataTwoSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl2);
        datasource.setUsername(username2);
        datasource.setPassword(password2);
        datasource.setDriverClassName(driverClassName2);
        datasource.setInitialSize(initialSize2);
        datasource.setMinIdle(minIdle2);
        datasource.setMaxActive(maxActive2);
        datasource.setMaxWait(maxWait2);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis2);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis2);
        datasource.setMaxEvictableIdleTimeMillis(minEvictableIdleTimeMillis2);
        datasource.setValidationQuery(validationQuery2);
        datasource.setTestWhileIdle(testWhileIdle2);
        datasource.setTestOnBorrow(testOnBorrow2);
        datasource.setTestOnReturn(testOnReturn2);
        datasource.setPoolPreparedStatements(poolPreparedStatements2);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize2);
        datasource.setConnectionProperties(connectionProperties2);
        return datasource;
    }

    /**
     * JDBC操作配置
     */
    @Bean(name = "dataOneTemplate")
    public JdbcTemplate dataOneTemplate(@Qualifier("dataOneSource") DruidDataSource dataOneSource) {
        return new JdbcTemplate(dataOneSource);
    }

    @Bean(name = "dataTwoTemplate")
    public JdbcTemplate dataTwoTemplate(@Qualifier("dataTwoSource") DruidDataSource dataTwoSource) {
        return new JdbcTemplate(dataTwoSource);
    }


    /**
     * Shard-JDBC 分库配置
     */
    @Bean
    public DataSource dataSource(@Qualifier("dataOneSource") DruidDataSource dataOneSource,
                                 @Qualifier("dataTwoSource") DruidDataSource dataTwoSource) throws Exception {
        ShardingRuleConfiguration shardJdbcConfig = new ShardingRuleConfiguration();
        shardJdbcConfig.getTableRuleConfigs().add(getTableRule01());
//        shardJdbcConfig.getTableRuleConfigs().add(getTableRule02());
        shardJdbcConfig.getTableRuleConfigs().add(getTableRule03());
        shardJdbcConfig.setDefaultDataSourceName("ds_1");
        Map<String, DataSource> dataMap = new LinkedHashMap<>();
        dataMap.put("ds_1", dataOneSource);
        dataMap.put("ds_2", dataTwoSource);
        Properties prop = new Properties();
        return ShardingDataSourceFactory.createDataSource(dataMap, shardJdbcConfig, new HashMap<>(), prop);
    }

    /**
     * Shard-JDBC 分表配置
     */
    private static TableRuleConfiguration getTableRule01() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("table_one");
        result.setActualDataNodes("ds_${1..2}.table_one_${1..3}");
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new DataSourceAlg()));
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new TableOneAlg()));
        return result;
    }
    private static TableRuleConfiguration getTableRule02() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("table_two");
        result.setActualDataNodes("ds_${1..2}.table_two_${1..3}");
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new DataSourceAlg()));
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new TableTwoAlg()));
        return result;
    }

    private static TableRuleConfiguration getTableRule03() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_user");
        result.setActualDataNodes("ds_${1..2}.t_user_${0..1}");
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("city_id", new DataSourceAlg()));
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("city_id", new TableThreeAlg()));
        return result;
    }
}
