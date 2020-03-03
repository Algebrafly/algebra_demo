package com.algebra.demo.conf.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author al
 * @date 2020/3/3 17:18
 * @description
 */
@Configuration
public class StrategyConfig {

    @Bean
    public MyPartitionKeyStrategy myPartitionKeyStrategy() {
        return new MyPartitionKeyStrategy();
    }

}
