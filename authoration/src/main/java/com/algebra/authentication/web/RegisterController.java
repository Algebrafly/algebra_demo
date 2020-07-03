package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.RegisterModel;
import com.algebra.authentication.domain.UserInfoVo;
import com.algebra.authentication.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wf.jwtp.annotation.Ignore;

/**
 * @author al
 * @date 2020/7/2 10:01
 * @description
 */
@Slf4j
@RestController
@Api(value = "Register", tags = "用户注册")
public class RegisterController {

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @Ignore
    public WebApiResult<UserInfoVo> register(RegisterModel registerModel){
        log.info("用户注册，接收到参数：{}", JSONUtil.toJsonStr(registerModel));

        // 写入用户名密码

        // 密码采用MD5加密

        // 赋予默认权限

        return WebApiResult.ok();
    }

}
