package com.algebra.demo.web;

import com.algebra.demo.util.RabbitMqSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/2/25 16:56
 * @description
 */
@RestController
@Api(tags = "MQ测试")
public class MqTestController {

    @Autowired
    private RabbitMqSender sender;

    @GetMapping("/mqTest01")
    public String mqTest01(String msg){
        sender.send(msg);
        return "successful";
    }

}
