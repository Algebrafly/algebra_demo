package com.algebra.demo.util;

import com.algebra.demo.conf.mq.DelayRabbitMqConfig;
import com.algebra.demo.conf.mq.NormalRabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

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

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * Direct 交换机模式
     */
    public void send(Object message){
        log.info("send topic message: " + message);
        amqpTemplate.convertAndSend(NormalRabbitMqConfig.QUEUE, message);
    }

    /**
     * Topic 交换机模式
     */
    public void sendTopic(Object message){
        log.info("send topic message: " + message);
        amqpTemplate.convertAndSend(NormalRabbitMqConfig.TOPIC_EXCHANGE,"topic.key1",message+"1");
        amqpTemplate.convertAndSend(NormalRabbitMqConfig.TOPIC_EXCHANGE,"topic.key2",message+"2");
    }

    /**
     * Fanout模式 交换机Exchange
     */
    public void sendFanout(Object message){
        log.info("send fanout message: " + message);
        amqpTemplate.convertAndSend(NormalRabbitMqConfig.FANOUT_EXCHANGE,"",message+"1");
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
        amqpTemplate.convertAndSend(NormalRabbitMqConfig.HEADERS_EXCHANGE,"",obj);
    }

    final RabbitTemplate.ConfirmCallback confirmCallback= new RabbitTemplate.ConfirmCallback() {

        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("correlationData: " + correlationData);
            System.out.println("ack: " + ack);
            if(!ack){
                System.out.println("异常处理....");
            }
        }

    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {

        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };

    //发送消息方法调用: 构建Message消息
    public void send(Object message, Map<String, Object> properties) throws Exception {
        MessageProperties mp = new MessageProperties();
        //在生产环境中这里不用Message，而是使用 fastJson 等工具将对象转换为 json 格式发送
        Message msg = new Message(message.toString().getBytes(),mp);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("1234567890"+new Date());
        rabbitTemplate.convertAndSend("BOOT-EXCHANGE-1", "boot.save", msg, correlationData);
    }

    public void sendLazy(Object message){
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("12345678909"+new Date());

        //发送消息时指定 header 延迟时间
        rabbitTemplate.convertAndSend(DelayRabbitMqConfig.LAZY_EXCHANGE, "lazy.boot", message,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置消息持久化
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        //message.getMessageProperties().setHeader("x-delay", "6000");
                        message.getMessageProperties().setDelay(60000);
                        return message;
                    }
                }, correlationData);
    }

}
