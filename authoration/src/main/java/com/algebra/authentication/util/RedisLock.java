package com.algebra.authentication.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @author al
 * @date 2021/8/9 17:49
 * @description
 */
@Slf4j
@Component
public class RedisLock {

    /**
     * 锁键
     */
    private final String lock_key = "redis_lock";

    /**
     * 锁过期时间
     */
    protected long internalLockLeaseTime = 30000;

    /**
     * 获取锁的超时时间
     */
    private long timeout = 999999;

    /**
     * SET命令的参数
     */
    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

    @Autowired
    JedisPool jedisPool;


    /**
     * 加锁
     *
     * @param id
     * @return
     */
    public boolean lock(String id) {

        try (Jedis jedis = jedisPool.getResource()) {
            long start = System.currentTimeMillis();
            for (; ; ) {
                //SET命令返回OK ，则证明获取锁成功
                String lock = jedis.set(lock_key, id, params);
                if ("OK".equals(lock)) {
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    return false;
                }
            }
        }
    }

    /**
     * 解锁
     *
     * @param id
     * @return
     */
    public boolean unlock(String id) {
        try (Jedis jedis = jedisPool.getResource()) {
            String script =
                    "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                            "   return redis.call('del',KEYS[1]) " +
                            "else" +
                            "   return 0 " +
                            "end";
            Object result = jedis.eval(script, Collections.singletonList(lock_key),
                    Collections.singletonList(id));
            return "1".equals(result.toString());
        }
    }


}
