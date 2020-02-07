package com.algebra.demoproduct.conf;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author al
 * @date 2020/2/6 11:59
 * @description
 */
@Configuration
public class RibbonServiceConfig {

    /**
     * 客户端
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 负载均衡算法
     */
    @Bean
    public IRule myRule() {
//        return new RoundRobinRule();
        return new RandomRule();
    }
}
