package com.algebra.demo.web;

import com.algebra.demo.config.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2021/12/30 15:35
 * @description
 */
@RestController
@Api(tags = "仅仅为了测试")
@RefreshScope
public class TestController {

    @Value("${name}")
    private String name;

    @Value("${password}")
    private String password;

    @GetMapping("/getOnlineConfigOfConsul")
    @ApiOperation("测试获取consul配置")
    public WebApiResult<String> getOnlineConfigOfConsul() {
        return WebApiResult.ok(name + ":" + password);
    }

}
