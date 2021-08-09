package com.algebra.authentication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.wf.jwtp.configuration.EnableJwtPermission;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.TimeZone;

/**
 * @author algebra
 * @desc 鉴权Demo
 */
@SpringBootApplication
@EnableJwtPermission
@MapperScan(basePackages = "com.algebra.authentication.mapper")
@EnableOpenApi
@EnableScheduling
public class AuthenticationApplication {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
