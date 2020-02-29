package com.algebra.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author MQ服务启动类
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.algebra.demo.mapper"})
public class DemoMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMqApplication.class, args);
    }

}
