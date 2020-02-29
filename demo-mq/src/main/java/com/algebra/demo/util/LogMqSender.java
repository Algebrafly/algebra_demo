package com.algebra.demo.util;

import com.algebra.demo.conf.mq.LogRabbitMqConfig;
import com.algebra.demo.conf.mq.NormalRabbitMqConfig;
import com.algebra.demo.entity.SysWebLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2020/2/29 12:30
 * @description
 */
@Component
@Slf4j
public class LogMqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void info(Object message){
        log.debug("info:{}", message);
        SysWebLog sysWebLog = new SysWebLog(String.valueOf(message));
        rabbitTemplate.convertAndSend(LogRabbitMqConfig.LOG_EXCHANGE,"log.info", sysWebLog);
    }

    public void debug(Object message){
        log.debug("debug:{}", message);
        SysWebLog sysWebLog = new SysWebLog(String.valueOf(message));
        rabbitTemplate.convertAndSend(LogRabbitMqConfig.LOG_EXCHANGE,"log.debug", sysWebLog);
    }

    public void error(Object message){
        log.debug("error:{}", message);
        SysWebLog sysWebLog = new SysWebLog(String.valueOf(message));
        rabbitTemplate.convertAndSend(LogRabbitMqConfig.LOG_EXCHANGE,"log.error", sysWebLog);
    }

}
