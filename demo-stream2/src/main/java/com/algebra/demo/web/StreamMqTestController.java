package com.algebra.demo.web;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/3/3 10:59
 * @description
 */
@Slf4j
@RestController
@Api(tags = "Stream-MQ测试")
public class StreamMqTestController {

    @GetMapping("/sendSimple")
    public void sendSimple(@RequestParam("msg") String msg){
        log.info(msg);
    }

}
