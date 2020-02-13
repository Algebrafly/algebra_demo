package com.algebra.demofastdep;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author al
 * 配置多数据源demo
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        MybatisAutoConfiguration.class})
@ServletComponentScan       //描到自己编写的servlet和filter druid监控
public class DemoShardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoShardApplication.class, args);
    }

}
