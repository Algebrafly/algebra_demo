package com.algebra.demoshard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author al
 * 配置多数据源demo
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "com.algebra.demoshard.mapper")
public class DemoShardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoShardApplication.class, args);
    }

}
