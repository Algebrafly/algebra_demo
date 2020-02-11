package com.algebra.demouser.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/1/21 13:45
 * @description
 */
@RestController
@Slf4j
public class UserController {

    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("name") String name){
        log.info("接收到请求，请求参数:{}", name);
        return "hello " + name;
    }
}
