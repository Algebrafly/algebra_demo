package com.algebra.demo.util.rpc.test.service.impl;


import com.algebra.demo.util.rpc.test.service.IHelloService;

/**
 * @author al
 * @date 2019/6/15 17:04
 * @description
 */
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }
}
