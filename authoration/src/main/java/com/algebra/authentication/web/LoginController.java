package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.LoginModel;
import com.algebra.authentication.domain.UserInfo;
import com.algebra.authentication.domain.UserInfoVo;
import com.algebra.authentication.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wf.jwtp.provider.Token;
import org.wf.jwtp.provider.TokenStore;

/**
 * @author al
 * @date 2020/7/2 10:01
 * @description
 */
@Slf4j
@RestController
@Api(value = "login", tags = "用户登录")
public class LoginController {

    @Qualifier("jdbcTokenStore")
    @Autowired
    private TokenStore tokenStore;

    @PostMapping("/login")
    @ApiOperation("登录")
    public WebApiResult<UserInfoVo> login(@RequestBody LoginModel loginModel){
        log.info("登录-接收到请求参数：{}", JSONUtil.toJsonStr(loginModel));
        UserInfoVo userInfoVo = new UserInfoVo();
        try {
            // 获取用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId("admin");
            // 校验密码

            // 获取部门-角色-菜单信息
            String[] permissions = new String[]{"system","front","menuList-1"};
            String[] roles = new String[]{"admin","customer","user"};

            // 签发token（过期时间2小时）
            Token token = tokenStore.createNewToken(userInfo.getUserId(), permissions, roles, 60*60*2);
            String tokenString = "access_"+token.getAccessToken();
            log.info("登录生成的Token："+tokenString);
            userInfoVo.setAccessToken(tokenString);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登录异常，异常信息：{}", e.getMessage());
            return WebApiResult.error(e);
        }
        return WebApiResult.ok(userInfoVo);
    }


    @PostMapping("/token/refresh")
    public WebApiResult<Token> login(String refreshToken) {
        Token token = tokenStore.refreshToken(refreshToken);
        System.out.println("access_token：" +"access_"+ token.getAccessToken());
        return WebApiResult.ok(token);
    }

}
