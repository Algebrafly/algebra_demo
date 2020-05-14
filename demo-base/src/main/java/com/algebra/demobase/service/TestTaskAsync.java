package com.algebra.demobase.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author al
 * @date 2020/5/14 9:51
 * @description
 */
@Component
@Slf4j
public class TestTaskAsync {
    @Async
    public void testAsync01(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("任务：asyncTask-01>>>>>>>>>>>>执行完毕！");
    }


    @Async
    public Future<String> testAsync02(){
        int seconds = 2;
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("I love you, always!");
        return new AsyncResult<>("思考了"+seconds+"秒！");
    }
}
