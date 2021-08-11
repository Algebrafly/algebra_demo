package com.algebra.authentication.web;

import com.algebra.authentication.util.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2021/8/9 10:40
 * @description
 */
@Component
@Slf4j
public class ScheduleTestController {

    private static boolean canDo = true;

    @Autowired
    RedisLock redisLock;

    @Autowired
    RedissonClient redissonClient;

//    @Scheduled(fixedRate = 1000 * 60 * 1)
    public void copyTask() {
        if (canDo) {
            canDo = false;
            redisLock.lock(Thread.currentThread().getName());
            log.info("定时任务Test1------->>>> 开始执行");
            int i = 0;
            while (true) {
                i++;
                log.info("i = {}, Thread:{}", i, Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i > 10) {
                    canDo = true;
                    redisLock.unlock(Thread.currentThread().getName());
                    break;
                }
            }
        }
    }


//    @Scheduled(fixedRate = 1000 * 5 * 1)
    public void copyTask2() {
        if (canDo) {
            canDo = false;
            RLock lock = redissonClient.getLock(Thread.currentThread().getName());
            lock.lock();
            log.info("定时任务Test2------->>>> 开始执行");
            int i = 0;
            while (true) {
                i++;
                log.info("i = {}, Thread:{}", i, Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i > 10) {
                    canDo = true;
                    lock.unlock();
                    break;
                }
            }
        }
    }


}
