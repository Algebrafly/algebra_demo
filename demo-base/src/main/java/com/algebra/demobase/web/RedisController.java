package com.algebra.demobase.web;

import com.algebra.demo.annotation.AccessLimit;
import com.algebra.demo.util.base.WebApiResult;
import com.algebra.demobase.conf.redis.RedisUtil;
import com.algebra.demobase.entity.vo.Person2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author al
 * @date 2019/12/6 14:07
 * @description redis 测试
 */
@RestController
@RequestMapping("/redis")
@Slf4j
@Api("redis 测试")
public class RedisController {

    private static int ExpireTime = 600;   // redis中存储的过期时间600s

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/redisSet")
    @ApiOperation("redisSet")
    // 限制一分钟内只能访问三次
    @AccessLimit(seconds = 60,maxCount = 3)
    public boolean redisSet(@RequestParam("key") String key, @RequestParam("value")String value){

        Person2 tom = Person2.builder().name("tom").birthday(new Date()).build();

        return redisUtil.set(key,tom,ExpireTime);
//        return redisUtil.set(key,value);
    }

    @GetMapping("redisGet")
    @ApiOperation("redisGet")
    public WebApiResult<Person2> redisGet(@RequestParam("key")String key){
        WebApiResult<Person2> result = new WebApiResult<>();
        result.setData((Person2) redisUtil.get(key));
        return result;
    }

    @GetMapping("expire")
    @ApiOperation("expire")
    public boolean expire(@RequestParam("key")String key){
        return redisUtil.expire(key,ExpireTime);
    }


}
