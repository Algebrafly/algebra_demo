package com.algebra.demoproduct.web;

import com.algebra.demoproduct.feign.IUserService;
import com.algebra.demoproduct.ribbon.UserRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/2/5 17:19
 * @description
 */
@RestController
public class ProductController {

    @Autowired
    IUserService userService;
    @Autowired
    UserRibbonService userRibbonService;

    @GetMapping("/productTest")
    public String productTest(String productName){
        String name = productName == null? "null":productName;
        return userService.getUserInfo(name);
    }

    @GetMapping("/productTest2")
    public String productTest2(String productName){
        String name = productName == null? "null":productName;
        return userRibbonService.getUserInfo(name);
    }

}
