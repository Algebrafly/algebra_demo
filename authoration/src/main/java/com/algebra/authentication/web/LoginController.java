package com.algebra.authentication.web;

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
import org.wf.jwtp.util.TokenUtil;

/**
 * @author al
 * @date 2020/7/2 10:01
 * @description
 */
@Slf4j
@RestController
@Api(value = "login", tags = "登陆")
public class LoginController {

    @Qualifier("jdbcTokenStore")
    @Autowired
    private TokenStore tokenStore;

    @PostMapping("/login")
    @ApiOperation("登陆")
    public WebApiResult<UserInfoVo> login(@RequestBody UserInfoVo userInfoVo){


        // 你的验证逻辑
        // ......
        // 签发token
        String[] permissions = new String[]{};
        String[] roles = new String[]{};
        Token token = tokenStore.createNewToken(userInfoVo.getUserId(), permissions, roles, 60*60*24*30);
        log.info("生成的Token："+token.getAccessToken());
        return WebApiResult.ok(userInfoVo);
    }


    @PostMapping("/token/refresh")
    public WebApiResult<Token> login(String refresh_token) {
        Token token = tokenStore.refreshToken(refresh_token);
        System.out.println("access_token：" + token.getAccessToken());
        return WebApiResult.ok(token);
    }

    public static void main(String[] args) {
        String header = "eyJhbGciOiJIUzI1NiJ9";


        String uid = TokenUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJleHAiOjE1OTYyNzA0MTJ9.04hgTQOF_nkYOEjsyNaBkX8iMuUHEOVdRMnZuaFzWiI",
                "c5b8410c16d987baf503efffb5131b7317c0a1212a043a387748223995780349");
        System.out.println(uid);
    }

}
