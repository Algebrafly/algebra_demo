package com.algebra.demofastdep.web;

import com.algebra.demofastdep.conf.RedisUtil;
import com.louislivi.fastdep.shirojwt.jwt.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/2/14 13:08
 * @description
 */
@RestController
public class NormalTestController {

    @Autowired
    private StringRedisTemplate redis1StringRedisTemplate;

    @Autowired
    private StringRedisTemplate redis2StringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin")
    @RequiresPermissions("1")
//    @RequiresAuthentication
    public String jwt() {
        return "ok!";
    }

    @GetMapping("front/login")
    public String token() {
        return jwtUtil.sign("1");
    }

    /**
     * redis test
     */
    @GetMapping("redis")
    public void redis() {
        System.out.println(redis1StringRedisTemplate.opsForValue().get("test"));
        System.out.println(redis2StringRedisTemplate.opsForValue().get("test"));
        System.out.println(redisUtil.redisTemplate("redis1").opsForValue().get("test"));
        System.out.println(redisUtil.stringRedisTemplate("redis2").opsForValue().get("test"));
    }
}
