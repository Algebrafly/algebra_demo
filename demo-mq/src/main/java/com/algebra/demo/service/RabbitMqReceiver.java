package com.algebra.demo.service;

import com.algebra.demo.conf.mq.NormalRabbitMqConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author al
 * @date 2020/2/25 16:46
 * @description
 */
@Slf4j
@Service
public class RabbitMqReceiver {

    /**
     * Direct 交换机模式
     */
    @RabbitListener(queues = NormalRabbitMqConfig.QUEUE)
    public void process(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.info("receive message : " + new String(message.getBody()));
    }

    public void receive(String message){
        log.info("receive message" + message);
    }

    /**
     * Topic 交换机模式
     */
    @RabbitListener(queues = NormalRabbitMqConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message){
        log.info("receive topic queue1 message: " + message);
    }

    @RabbitListener(queues = NormalRabbitMqConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message){
        log.info("receive topic queue2 message: " + message);
    }

    /**
     * Fanout模式 交换机Exchange
     */
    @RabbitListener(queues = NormalRabbitMqConfig.FANOUT_QUEUE1)
    public void receiveFanout1(String message){
        log.info("receive fanout queue1 message: " + message);
    }

    @RabbitListener(queues = NormalRabbitMqConfig.FANOUT_QUEUE2)
    public void receiveFanout2(String message){
        log.info("receive fanout queue2 message: " + message);
    }

    /**
     * Header模式 交换机Exchange
     */
    @RabbitListener(queues = NormalRabbitMqConfig.HEADERS_QUEUE)
    public void receiveFanout2(byte[] message){
        log.info("receive headers queue message: " + new String(message));
    }

    @RabbitListener(queues = "MQ.LazyQueue")
    @RabbitHandler
    public void onLazyMessage(Message msg, Channel channel) throws IOException{
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
        System.out.println("lazy receive " + new String(msg.getBody()));

    }

}
