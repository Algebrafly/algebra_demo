package com.algebra.docker.docker.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2022/2/7 16:17
 * @description
 */
@RestController
public class HelloWorldController {

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(value = "name", required = false, defaultValue = "world") String name) {
        return "hello " + name;
    }

}
