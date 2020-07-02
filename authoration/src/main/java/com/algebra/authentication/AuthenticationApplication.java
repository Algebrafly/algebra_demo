package com.algebra.authentication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wf.jwtp.configuration.EnableJwtPermission;

/**
 * @author algebra
 * @desc 鉴权Demo
 */
@SpringBootApplication
@EnableJwtPermission
//@MapperScan(basePackages = "com.algebra.authentication.mapper")
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
