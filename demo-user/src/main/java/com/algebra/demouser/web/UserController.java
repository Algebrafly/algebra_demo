package com.algebra.demouser.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/1/21 13:45
 * @description
 */
@RestController
public class UserController {

    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("name") String name){
        System.out.println("接收到请求！");
        return "hello " + name;
    }
}
