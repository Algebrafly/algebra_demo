package com.algebra.demoproduct.feign;

import com.algebra.demoproduct.feign.hystrix.UserServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author al
 * @date 2020/2/5 17:55
 * @description
 */
@FeignClient(value = "user-center",fallback = UserServiceFallbackImpl.class)
public interface IUserService {

    @RequestMapping(value = "/user/getUserInfo",method = RequestMethod.GET)
    String getUserInfo(@RequestParam("name") String name);

}
