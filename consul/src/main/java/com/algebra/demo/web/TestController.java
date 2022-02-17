package com.algebra.demo.web;

import com.algebra.demo.config.WebApiResult;
import com.algebra.demo.entity.PersonConfigProps;
import com.algebra.demo.entity.TestValidVo;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author al
 * @date 2021/12/30 15:35
 * @description
 */
@RestController
@Api(tags = "仅仅为了测试")
@RefreshScope
@Slf4j
public class TestController {

    @Value("${name}")
    private String name;

    @Value("${password}")
    private String password;

    @Value("${aclToken}")
    private String aclToken;

    @Autowired
    PersonConfigProps personConfigProps;


    @GetMapping("/getOnlineConfigOfConsul")
    @ApiOperation("测试获取consul配置")
    public WebApiResult<String> getOnlineConfigOfConsul(HttpServletRequest request) {
        log.info("路由转发参数: {}", JSONObject.toJSONString(request.getParameterMap()));
        return WebApiResult.ok(name + ":" + password + ":" + aclToken);
    }

    @GetMapping("/getOnlineConfigOfConsul2")
    @ApiOperation("测试获取consul配置-props")
    public WebApiResult<String> getOnlineConfigOfConsul2() {
        return WebApiResult.ok(personConfigProps.getUsername() + ":" + personConfigProps.getAclToken());
    }


    @GetMapping("/getOnlineConfigOfConsul3")
    @ApiOperation("测试网关超时熔断")
    public WebApiResult<String> getOnlineConfigOfConsul3() {
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return WebApiResult.ok(personConfigProps.getUsername() + ":" + personConfigProps.getAclToken());
    }

    @PostMapping("/testValidParam1")
    @ApiOperation("测试参数校验1")
    public WebApiResult<String> testValidParam1(@Validated @RequestBody TestValidVo vo, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                String error = bindingResult.getFieldError().getDefaultMessage();
                return WebApiResult.error(error);
            }
            log.info("参数为：{}", JSONObject.toJSONString(vo));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return WebApiResult.ok("参数校验通过！");
    }

    @PostMapping("/testValidParam2")
    @ApiOperation("测试参数校验2")
    public WebApiResult<String> testValidParam2(@Validated @RequestBody TestValidVo vo) {
        log.info("参数为：{}", JSONObject.toJSONString(vo));

        return WebApiResult.ok("参数校验通过！");
    }

}
