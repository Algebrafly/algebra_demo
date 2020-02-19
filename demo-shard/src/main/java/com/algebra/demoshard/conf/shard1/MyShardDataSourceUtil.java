package com.algebra.demoshard.conf.shard1;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author al
 * @date 2020/2/18 11:14
 * @description 单纯使用yaml文件配置
 */
//@Configuration
public class MyShardDataSourceUtil {

    @Bean("dataSource")
    public DataSource dataSource() throws IOException, SQLException {
        return YamlShardingDataSourceFactory.createDataSource(new File("application-shard1.yml"));
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
