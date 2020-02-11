package com.algebra.demoproduct.web;

import com.algebra.demoproduct.feign.IUserService;
import com.algebra.demoproduct.ribbon.UserRibbonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author al
 * @date 2020/2/5 17:19
 * @description
 */
@RestController
@Slf4j
public class ProductController {

    @Autowired
    IUserService userService;
    @Autowired
    UserRibbonService userRibbonService;

    /**
     * feign 实现的服务调用
     */
    @GetMapping("/productTest")
    public String productTest(HttpServletRequest request, @RequestParam("productName") String productName){
        String name = productName == null? "null":productName;
        log.info("接收到参数：{}",productName);
        return userService.getUserInfo(name);
    }

    /**
     * ribbon 实现的服务调用
     */
    @GetMapping("/productTest2")
    public String productTest2(String productName){
        String name = productName == null? "null":productName;
        return userRibbonService.getUserInfo(name);
    }

}
