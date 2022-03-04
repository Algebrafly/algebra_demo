package com.algebra.gateway.util;

import org.redisson.api.RLongAdder;
import org.redisson.api.RMap;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.client.codec.StringCodec;

/**
 * @author al
 * @date 2022/3/4 14:57
 * @description
 */
public class RedissonUtilTest {

    public static void main(String[] args) {

//        RTopic topic = RedissonUtil.getRedissonClient().getTopic("test-redis-topic");
//        topic.addListener(String.class, new MessageListener<String>() {
//            @Override
//            public void onMessage(CharSequence charSequence, String s) {
//                System.out.println(Thread.currentThread().getName() + "接收到消息：" + s);
//            }
//        });

        RedissonClient redisson = RedissonUtil.getRedissonClient();
//        RLongAdder longAdder = redisson.getLongAdder("myLongAdder");
//        longAdder.add(12);
//        longAdder.increment();
//        System.out.println(longAdder.sum());

    }

}
