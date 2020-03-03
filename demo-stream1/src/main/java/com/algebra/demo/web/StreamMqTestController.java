package com.algebra.demo.web;

import com.algebra.demo.util.StreamMqSender;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/3/3 10:59
 * @description
 */
@Slf4j
@RestController
@Api(tags = "Stream-MQ测试")
public class StreamMqTestController {

    @Autowired
    Processor processor;
    @Autowired
    StreamMqSender streamMqSender;

    @GetMapping("/sendSimple")
    public void sendSimple(@RequestParam("msg") String msg){
        processor.output().send(MessageBuilder.withPayload(msg).build());
    }

    @GetMapping("/sendToOtherService")
    public void sendToOtherService(){
        for(int i = 0; i < 5; i ++){
            try {
                Map<String, Object> properties = new HashMap<String, Object>();
                properties.put("SERIAL_NUMBER", "12345");
                properties.put("BANK_NUMBER", "abc");
                properties.put("PLAT_SEND_TIME", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
                // 正常分组
//                streamMqSender.sendMessage("Hello, I am amqp sender num :" + i, properties);
                // 测试消息分区：同一类消息只发送给同一个消费者(partitionKeyExpression=payload)
                // partitionKeyExpression=myPartitionKeyStrategy自定义分区规则
                streamMqSender.sendMessage("Hello, I am amqp sender num : 0", properties);

            } catch (Exception e) {
                System.out.println("--------error-------");
                e.printStackTrace();
            }
        }
    }


}
