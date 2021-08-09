package com.algebra.authentication.web;

import lombok.extern.slf4j.Slf4j;
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

    @Scheduled(fixedRate = 1000 * 60 * 1)
    public void copyTask() {
        if (canDo) {
            canDo = false;
            log.info("定时任务Test------->>>> 开始执行");
            int i = 0;
            while (true) {
                i++;
                log.info("i = {}, Thread:{}", i, Thread.currentThread().getName());
                if (i > 5) {
                    canDo = true;
                    break;
                }
            }
        }
    }

}
