package com.algebra.demobase.web;

import com.algebra.demo.annotation.TestAspectAnnotation;
import com.algebra.demobase.service.impl.TestAspectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/2/4 12:17
 * @description
 */
@RestController
@Api(value = "AspectTest",tags = "切面测试类")
@Slf4j
public class AspectTestController {

    @Autowired
    TestAspectService aspectService;

    @GetMapping("/test1")
    @ApiOperation(value = "测试-1")
    public String test1(){
        return "hello";
    }

    @GetMapping("/test2")
    @ApiOperation(value = "测试-2")
    public String test2(){
        log.info("hello test2");
        return aspectService.testService();
    }

}
