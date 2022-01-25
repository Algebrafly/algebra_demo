package com.algebra.gateway.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    private TokenUser globalToken;

    private List<String> ignoreUrlList;

}
