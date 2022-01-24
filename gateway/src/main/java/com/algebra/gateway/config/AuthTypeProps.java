package com.algebra.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author al
 * @date 2022/1/24 17:56
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "auth")
@Data
public class AuthTypeProps {

    private String type;

    private String name;

}
