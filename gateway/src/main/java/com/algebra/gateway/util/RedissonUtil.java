package com.algebra.gateway.util;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author al
 * @date 2022/3/3 17:42
 * @description
 */
public class RedissonUtil {

    private final static RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setDatabase(0).setAddress("redis://192.168.6.63:6379");
        redissonClient = Redisson.create(config);
    }

    public static RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public static void main(String[] args) {

//        RTopic topic = redissonClient.getTopic("test-redis-topic");
//        long hello = topic.publish("hello");
//        System.out.println(Thread.currentThread().getName() + "发送完消息：" + hello);
        RMap<String, Object> redissonMap = redissonClient.getMap("myMap", StringCodec.INSTANCE);
        redissonMap.put("ab", 1234);



    }
}
