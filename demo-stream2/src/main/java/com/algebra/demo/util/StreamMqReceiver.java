package com.algebra.demo.util;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author al
 * @date 2020/3/3 15:11
 * @description
 */
@EnableBinding(BaristaIn.class)
@Component
@Slf4j
public class StreamMqReceiver {

    @StreamListener(BaristaIn.MY_INPUT)
    public void receive(Message<Object> message) throws IOException {
        //手工签收必须要有channel与deliveryTag
        Channel channel = (com.rabbitmq.client.Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        log.info("Input Stream 1 接受数据：{}", message);
        //批量签收设置为false
//        channel.basicAck(deliveryTag, false);

    }

}
