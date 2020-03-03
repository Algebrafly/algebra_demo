package com.algebra.demo.service.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2020/3/3 10:56
 * @description
 */
@Component
@Slf4j
@EnableBinding(Processor.class)
public class StreamMqReceiver {

    @StreamListener(Processor.INPUT)
    public void process(String message){
        log.info("[rabbitMq Stream]接收到消息：{}", message);
        // do sth.

    }

}
