package com.algebra.demouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author al
 */
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.algebra.demouser.conf")
public class DemoUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoUserApplication.class, args);
    }
}
