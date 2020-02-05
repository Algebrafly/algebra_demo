package com.algebra.demobase.web;

import com.algebra.demo.util.base.WebApiResult;
import com.algebra.demobase.entity.domain.SysUser;
import com.algebra.demobase.service.ISysUserService;
import com.algebra.demobase.service.impl.TestAspectService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
    @Autowired
    ISysUserService sysUserService;

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

    @PostMapping("getSysUserInfo")
    @ApiOperation(value = "获取登陆用户信息")
    public WebApiResult<SysUser> getSysUserInfo(@RequestBody SysUser sysUser){
        log.info("获取登陆用户详细信息，请求参数：{}", JSONObject.toJSON(sysUser));
        WebApiResult<SysUser> result = new WebApiResult<>();
        try {
            SysUser userInfoOne = sysUserService.getUserInfoOne(sysUser.getUserId(), sysUser);
            result.setData(userInfoOne);
            result.setMessage("获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取登陆用户详细信息接口异常，异常信息 exp-msg：{}",e.getMessage());
            result.setSuccess(false);
            result.setMessage("获取登陆用户详细信息接口异常");
        }
        return result;
    }

    @PostMapping("setSysUserInfo")
    @ApiOperation(value = "添加登陆用户信息")
    public WebApiResult<String> setSysUserInfo(@RequestBody SysUser sysUser){
        log.info("添加登陆用户详细信息接口，请求参数：{}", JSONObject.toJSON(sysUser));
        WebApiResult<String> result = new WebApiResult<>();
        try {
            String userId = UUID.randomUUID().toString();
            sysUser.setUserId(userId);
            int i = sysUserService.insertSelective(sysUser);
            result.setData(String.valueOf(i));
            result.setMessage("添加成功！");
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加登陆用户详细信息接口异常，异常信息 exp-msg：{}",e.getMessage());
            result.setSuccess(false);
            result.setMessage("添加登陆用户详细信息接口异常");
        }
        return result;
    }

}
