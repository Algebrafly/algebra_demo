package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.service.SysUserService;
import com.algebra.authentication.util.CommonUtil;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.vo.RegisterModel;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wf.jwtp.annotation.Ignore;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @author al
 * @date 2020/7/2 10:01
 * @description
 */
@Slf4j
@RestController
@Api(value = "Register", tags = "用户注册")
public class RegisterController {

    @Autowired
    SysUserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @Ignore
    public WebApiResult<String> register(HttpServletRequest request, @RequestBody RegisterModel registerModel){
        log.info("用户注册，接收到参数：{}", JSONUtil.toJsonStr(registerModel));

        try {
            if(registerModel.isNeedVerifyCode()){
                String verifyCode = registerModel.getVerifyCode();
                //判断验证码
                String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
                if (StringUtils.isNotBlank(verifyCode) && StringUtils.isNotBlank(rightCode) && verifyCode.equals(rightCode)) {
                    //验证码通过
                    log.info("验证码通过！");
                } else {
                    return WebApiResult.error("验证码错误！");
                }
            }

            String userName = registerModel.getUserName();
            // 验证账号重复
            SysUser userInfoByName = userService.getUserInfoByName(userName);
            if(userInfoByName != null){
                return WebApiResult.error("账号已存在！");
            }
            String password = registerModel.getPassword();
            String confirmPassword = registerModel.getConfirmPassword();
            // 验证密码一致性
            if(!password.equals(confirmPassword)){
                return WebApiResult.error("两次密码不一致！");
            }
            // 密码采用MD5加密
            String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
            // 组装DO基本信息
            SysUser sysUser = new SysUser();
            sysUser.setUsrId(CommonUtil.createPrimaryKey("user"));
            sysUser.setUsername(userName);
            sysUser.setPassword(md5Pwd);
            sysUser.setEmail(registerModel.getEmail());
            sysUser.setPhone(registerModel.getMobile());
            sysUser.setRealName(registerModel.getRealName());
            sysUser.setCreateTime(new Date());
            sysUser.setUsrStatus("1");

            // 赋予默认权限



        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户注册失败，原因：{}", e.getMessage());
        }

        return WebApiResult.ok();
    }

}
