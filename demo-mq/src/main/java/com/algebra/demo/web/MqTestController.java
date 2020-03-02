package com.algebra.demo.web;

import com.algebra.demo.entity.CouponForm;
import com.algebra.demo.entity.User;
import com.algebra.demo.util.LogMqSender;
import com.algebra.demo.util.RabbitMqSender;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author al
 * @date 2020/2/25 16:56
 * @description
 */
@Slf4j
@RestController
@Api(tags = "MQ测试")
public class MqTestController {

    @Autowired
    private RabbitMqSender sender;

    @Autowired
    LogMqSender logMqSender;

    @GetMapping("/mqTest01")
    public String mqTest01(String msg){
        sender.sendLazy(msg);
        return "successful";
    }

    @PostMapping("/mqTest02")
    public String mqTest02(@Valid @RequestBody User msg, BindingResult bindingResult){
        // 默认情况下，如果校验失败会抛javax.validation.ConstraintViolationException异常，可以用统一异常处理去对这些异常做处理
        if(bindingResult.hasErrors()){
            for (ObjectError allError : bindingResult.getAllErrors()) {
                return allError.getDefaultMessage();
            }
        }
        sender.sendObj(msg);
        return "successful";
    }

    @GetMapping("/mqTestTopic")
    public String mqTest03(@RequestParam String msg){
        sender.sendTopic(msg);
        return "successful";
    }


    @GetMapping("/helloLog")
    public String logMqTest(@RequestParam("name") String name){

        // 1.当前类名、方法名、线程号等信息
        // 2.下一步实现占位符匹配（Appender实现）
        logMqSender.info("[测试类]请求信息："+name);
        log.info("[测试类]请求信息：{}",name);
        return "hello "+name;
    }

    @PostMapping("/validatorTest")
    public String validatorTest(@Valid @RequestBody CouponForm couponForm){

        log.info("接收到参数：{}", JSONObject.toJSONString(couponForm));
        return "";
    }


}
