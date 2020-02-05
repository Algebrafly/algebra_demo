package com.algebra.demoproduct.feign.hystrix;

import com.algebra.demoproduct.feign.IUserService;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2020/2/5 18:44
 * @description
 */
@Component
public class UserServiceFallbackImpl implements IUserService {
    @Override
    public String getUserInfo(String name) {
        return "sorry！user-service is disable now!";
    }
}
