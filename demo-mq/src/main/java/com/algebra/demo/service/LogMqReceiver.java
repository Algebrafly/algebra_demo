package com.algebra.demo.service;

import com.algebra.demo.conf.mq.LogRabbitMqConfig;
import com.algebra.demo.entity.SysWebLog;
import com.algebra.demo.entity.User;
import com.algebra.demo.util.LogMqSender;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author al
 * @date 2020/2/29 12:29
 * @description
 */
@Slf4j
@Service
public class LogMqReceiver {

    @Autowired
    SysWebLogService webLogService;

    @RabbitListener(queues = LogRabbitMqConfig.LOG_QUEUE)
    @RabbitHandler
    public void onUserMessage(@Payload SysWebLog sysWebLog, Channel channel, @Headers Map<String,Object> headers) throws IOException {
        long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ack
        channel.basicAck(deliveryTag,true);
        log.info("log info receive msg: {}", JSONObject.toJSONString(sysWebLog));

        // 可以设置记录日志方式：数据库，文件，仅控制台打印等
        webLogService.insertSelective(sysWebLog);
    }

}
