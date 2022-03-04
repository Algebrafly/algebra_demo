package com.algebra.gateway.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author al
 * @date 2022/3/3 17:39
 * @description
 */
@Configuration
public class RedissonConfig {

    String host;
    String port;
    int database;
    String password;

    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer().setDatabase(0).setAddress("redis://192.168.6.63:6379");
        return Redisson.create(config);
    }

}
