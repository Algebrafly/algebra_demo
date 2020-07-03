package com.algebra.authentication.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/7/3 10:27
 * @description
 */
@Data
@ApiModel("登录参数")
public class LoginModel implements Serializable {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String verifyCode;

    @ApiModelProperty("记住我")
    private String rememberMe;

}
