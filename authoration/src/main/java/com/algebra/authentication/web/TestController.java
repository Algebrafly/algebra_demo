package com.algebra.authentication.web;

import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wf.jwtp.annotation.Ignore;
import org.wf.jwtp.annotation.RequiresPermissions;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2020/7/2 10:01
 * @description
 */
@Slf4j
@RestController
@Api(value = "Test", tags = "测试接口")
public class TestController {

    @GetMapping("/todoList")
    @ApiIgnore
    @Ignore
    public List<String> getTodoList(){
        List<String> al = new ArrayList<>();
        al.add("Hello");
        al.add("react");
        return al;
    }


    @GetMapping("/loginTest")
    @ApiOperation("测试鉴权")
    public WebApiResult<UserInfoVo> loginTest(@Parameter(description = "信息") @RequestParam(value = "msg",defaultValue = "hello") String msg){
        
        log.info("登录即可访问！");

        return WebApiResult.ok(msg);
    }


    @GetMapping("/loginTest2")
    @ApiOperation("测试鉴权-2")
    @RequiresPermissions("1")
    public WebApiResult<UserInfoVo> loginTest2(String msg){

        log.info("登录&拥有system权限可访问！");


        return WebApiResult.ok(msg);
    }

}
