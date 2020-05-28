package com.algebra.demobase.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author al
 * @date 2020/5/28 11:08
 * @description
 */
@RestController
@Slf4j
@Api(value = "threadPool", tags = "线程池")
public class ThreadPoolTestController {

    @Resource(name = "testFxbDrawExecutor")
    private ExecutorService threadPool;

    @PostMapping
    @ApiOperation("开启线程池拼接字符串")
    public String testSimpleThreadPool(@RequestBody List<String> srcList) {
        log.info("接受到参数：{}", JSONObject.toJSONString(srcList));
        StringBuilder strUrl = new StringBuilder("http://test.com");
        try {
            List<List<String>> partition = Lists.partition(srcList, 5);
            CountDownLatch downLatch = new CountDownLatch(partition.size());
            for (int i = 0; i < partition.size(); i++) {
                List<String> strList = partition.get(i);

                threadPool.submit(() -> {
                    try {
                        for (int e = 0; e < strList.size(); e++) {
                            strUrl.append("?value[").append(e).append("]=").append(strList.get(e)).append("&");
                        }
                    } catch (Exception e) {
                        log.error("程序异常,{}", e.getMessage());
                    } finally {
                        downLatch.countDown();
                    }
                });
            }
            downLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exp:" + e.getMessage());
        }
        return strUrl.toString();
    }

}
