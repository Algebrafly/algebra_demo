package com.algebra.demobase.web;

import com.algebra.demobase.service.TestTaskAsync;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author al
 * @date 2020/5/14 9:52
 * @description
 */
@RestController
@Slf4j
@Api(value = "AsyncTest", tags = "异步Async")
public class AsyncTestController {

    @Autowired
    private TestTaskAsync testTaskAsync;


    @GetMapping("/testAsync1")
    @ApiOperation("spring-boot异步注解测试")
    public void testAsync1(){

        log.info("task-01 before---------------------->>>>");
        testTaskAsync.testAsync01();
        log.info("task-01 after----------------------->>>>");

    }

    @GetMapping("/testAsync2")
    @ApiOperation("spring-boot异步注解测试（有返回值）")
    public void testAsync2(){

        log.info("你不爱我了么?");
        Future<String> future = testTaskAsync.testAsync02();
        log.info("你竟无话可说, 我们分手吧... ...");
        try {
            Thread.sleep(3000);
            log.info(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

}
