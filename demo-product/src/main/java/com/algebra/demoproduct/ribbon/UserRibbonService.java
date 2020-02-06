package com.algebra.demoproduct.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author al
 * @date 2020/2/6 11:52
 * @description
 */
@Component
public class UserRibbonService {

    @Autowired
    RestTemplate restTemplate;

    public String getUserInfo(String name){
        return restTemplate.getForObject("http://user-center/user/getUserInfo?name="+name
        ,String.class);
    }

}
