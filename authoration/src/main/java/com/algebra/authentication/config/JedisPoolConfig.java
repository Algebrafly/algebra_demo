package com.algebra.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author al
 * @date 2021/8/9 17:57
 * @description
 */
@Configuration
public class JedisPoolConfig {

    @Bean("jedisPool")
    public JedisPool getJedisPool(){
        return new JedisPool();
    }

}
