package com.algebra.demo.conf.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/2/28 15:02
 * @description
 */
@Configuration
public class DelayRabbitMqConfig {

    public static final String LAZY_EXCHANGE = "Ex.LazyExchange";
    public static final String LAZY_QUEUE = "MQ.LazyQueue";
    public static final String LAZY_KEY = "lazy.#";

    @Bean
    public TopicExchange lazyExchange(){
        Map<String, Object> pros = new HashMap<>();
        //设置交换机支持延迟消息推送
//        pros.put("x-delayed-type", "direct");
        pros.put("x-delayed-message", "topic");
        TopicExchange exchange = new TopicExchange(LAZY_EXCHANGE, true, false, pros);
        exchange.setDelayed(true);
        return exchange;
    }

    @Bean
    public Queue lazyQueue(){
        return new Queue(LAZY_QUEUE, true);
    }

    @Bean
    public Binding lazyBinding(){
        return BindingBuilder.bind(lazyQueue()).to(lazyExchange()).with(LAZY_KEY);
    }

    /**
     * 消息序列化
     * 要采用其他类型的消息转换器，我们可以对其进行设置
     * @return
     */
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
//        container.setMessageConverter(new Jackson2JsonMessageConverter());
//        // 默认采用下面的这种转换器
//        // container.setMessageConverter(new SimpleMessageConverter());
//        return container;
//    }

}
