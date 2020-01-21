package com.algebra.demobase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author base服务启动类
 */
@SpringBootApplication
@MapperScan(value = "com.algebra.demobase.mapper")
public class DemoBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBaseApplication.class, args);
    }

}
