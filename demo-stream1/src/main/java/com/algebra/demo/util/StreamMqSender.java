package com.algebra.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author al
 * @date 2020/3/3 11:34
 * @description
 */
@EnableBinding(BaristaOut.class)
@Component
@Slf4j
public class StreamMqSender {

    @Autowired
    private BaristaOut baristaOut;

    public String sendMessage(Object message, Map<String, Object> properties) throws Exception {
        try{
//            MessageHeaders mhs = new MessageHeaders(properties);
//            Message<Object> msg = MessageBuilder.createMessage(message, mhs);
            Message<Object> build = MessageBuilder.withPayload(message).build();
            boolean sendStatus = baristaOut.myOutput().send(build);
            log.info("--------------sending -------------------");
            log.info("发送数据：{}, sendStatus: {}", message, sendStatus);
        }catch (Exception e){
            log.info("-------------error-------------");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());

        }
        return null;
    }

}
