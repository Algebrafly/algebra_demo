package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.service.rbac.SysUserService;
import com.algebra.authentication.util.MdcConstant;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.util.userlog.SysLogAsp;
import com.algebra.authentication.vo.LoginModel;
import com.algebra.authentication.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.wf.jwtp.annotation.Ignore;
import org.wf.jwtp.provider.Token;
import org.wf.jwtp.provider.TokenStore;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @author al
 * @date 2020/7/2 10:01
 * @description
 */
@Slf4j
@RestController
@Api(value = "Login", tags = "用户登录")
public class LoginController {

    @Qualifier("jdbcTokenStore")
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    SysUserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    @SysLogAsp
    @Ignore
    public WebApiResult<UserInfoVo> login(@RequestBody LoginModel loginModel) {
        log.info("登录-接收到请求参数：{}", JSONUtil.toJsonStr(loginModel));
        UserInfoVo userInfoVo = new UserInfoVo();
        try {
            String userName = loginModel.getUserName();
            String password = loginModel.getPassword();
            String rememberMe = loginModel.getRememberMe();

            String pwdByMd5 = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            // 获取用户信息
            SysUser userInfo = userService.getUserInfoByName(userName);
            if (userInfo == null) {
                return WebApiResult.error("用户不存在！");
            }
            // 校验密码
            if (!pwdByMd5.equals(userInfo.getPassword())) {
                return WebApiResult.error("密码错误！");
            }

            // 获取部门-角色-菜单信息
            String[] permissions = new String[]{"system", "front", "menuList-1"};
            String[] roles = new String[]{"admin", "customer", "user"};

            // 签发token（过期时间2小时）
            long expireTime = 60 * 60 * 2;
            if ("1".equals(rememberMe)) {
                expireTime = 60 * 60 * 24;
            }
            Token token = tokenStore.createNewToken(userInfo.getUsrId(), permissions, roles, expireTime);
            String tokenString = "access_" + token.getAccessToken();
            log.info("登录生成的Token：{}", tokenString);
            userInfoVo.setAccessToken(tokenString);
            userInfoVo.setUser(userInfo);
            MDC.put(MdcConstant.USER_INFO, JSONUtil.toJsonStr(userInfo));
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
        log.info("access_token：{}", ("access_" + token.getAccessToken()));
        return WebApiResult.ok(token);
    }


    @ApiOperation("注销登录")
    @GetMapping("logout")
    public WebApiResult<String> logout(@RequestParam("username") String username){
        log.info("注销登录，用户名：{}", username);
        // 清除token

        return null;
    }

}
