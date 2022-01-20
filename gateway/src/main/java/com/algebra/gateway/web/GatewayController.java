package com.algebra.gateway.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2022/1/19 17:57
 * @description
 */
@RestController
@RequestMapping("/fallback")
public class GatewayController {

    @RequestMapping("")
    public String fallback(){
        return "error fallback method , " + System.currentTimeMillis();
    }
    @RequestMapping("/test1")
    public String fallbackTest1(){
        return "error fallbackTest1 method , " + System.currentTimeMillis();
    }
}
