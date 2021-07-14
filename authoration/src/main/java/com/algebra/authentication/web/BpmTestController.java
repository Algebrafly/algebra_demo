package com.algebra.authentication.web;

import com.algebra.authentication.util.bpm.RunCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author al
 * @date 2021/7/14 15:43
 * @description
 */
@Slf4j
@RestController
@Api(value = "BpmTestController", tags = "BPM测试接口")
public class BpmTestController {

    @Resource
    private RunCase runCase;

    @GetMapping("/say")
    @ApiOperation("say")
    public String say() {
        return "Hello,compileflow.";
    }

    @GetMapping("/run")
    @ApiOperation("run")
    public String run() {
        runCase.run();
        return "Run ok.";
    }

    @GetMapping("/run2")
    @ApiOperation("run2")
    public String run2() {
        runCase.run2();
        return "Run ok.";
    }
    @GetMapping("/run3")
    @ApiOperation("run3")
    public String run3() {
        runCase.run3();
        return "Run ok.";
    }

}
