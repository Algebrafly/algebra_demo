package com.algebra.demo.util;

import com.algebra.demo.conf.mq.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author al
 * @date 2020/2/25 16:46
 * @description
 */
@Service
@Slf4j
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * Direct 交换机模式
     */
    public void send(Object message){
        log.info("send topic message: " + message);
        amqpTemplate.convertAndSend(RabbitMqConfig.QUEUE, message);
    }

    /**
     * Topic 交换机模式
     */
    public void sendTopic(Object message){
        log.info("send topic message: " + message);
        amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,"topic.key1",message+"1");
        amqpTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,"topic.key2",message+"2");
    }

    /**
     * Fanout模式 交换机Exchange
     */
    public void sendFanout(Object message){
        log.info("send fanout message: " + message);
        amqpTemplate.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE,"",message+"1");
    }

    /**
     * Header模式 交换机Exchange
     *"header1","value1"要与队列初始化的时候一样
     */
    public void sendHeaders(Object message){
        log.info("send headers message: " + message);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1","value1");
        properties.setHeader("header2","value2");
        Message obj = new Message(message.toString().getBytes(),properties);
        amqpTemplate.convertAndSend(RabbitMqConfig.HEADERS_EXCHANGE,"",obj);
    }

}
