package com.algebra.demo.conf.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author al
 * @date 2020/2/29 12:28
 * @description 日志MQ配置
 */
@Configuration
public class LogRabbitMqConfig {

    public static final String LOG_EXCHANGE = "Ex.LogExchange";
    public static final String LOG_QUEUE = "MQ.LogQueue";
    public static final String LOG_KEY = "log.#";

    @Bean
    public TopicExchange logExchange(){
        return new TopicExchange(LOG_EXCHANGE);
    }

    @Bean
    public Queue logQueue(){
        return new Queue(LOG_QUEUE, true);
    }

    @Bean
    public Binding logBinding(){
        return BindingBuilder.bind(logQueue()).to(logExchange()).with(LOG_KEY);
    }



}
