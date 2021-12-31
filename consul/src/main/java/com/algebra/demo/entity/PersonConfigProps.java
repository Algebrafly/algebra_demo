package com.algebra.demo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author al
 * @date 2021/12/31 11:24
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "person.login")
@Data
public class PersonConfigProps {
    private String username;
    private String aclToken;
}
