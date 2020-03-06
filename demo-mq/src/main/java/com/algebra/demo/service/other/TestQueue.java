package com.algebra.demo.service.other;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author al
 * @date 2020/3/6 15:18
 * @description
 */
@Component
@Slf4j
public class TestQueue {

    @RabbitListener(queues = "MQ.LazyQueue")
    @RabbitHandler
    public void onLazyMessage(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        //手工ack
        channel.basicAck(deliveryTag, true);
        log.info("lazy receive " + new String(msg.getBody()));

    }

}
