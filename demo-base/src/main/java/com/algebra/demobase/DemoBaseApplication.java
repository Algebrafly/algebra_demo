package com.algebra.demobase;

import com.algebra.demo.auth.EnableDataFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author base服务启动类
 */
@SpringBootApplication
@MapperScan(value = "com.algebra.demobase.mapper")
@EnableAsync
@EnableDataFilter
public class DemoBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBaseApplication.class, args);
    }

}
