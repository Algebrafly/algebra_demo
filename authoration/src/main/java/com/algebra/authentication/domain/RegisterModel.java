package com.algebra.authentication.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/7/3 11:03
 * @description
 */
@Data
@ApiModel("登录参数")
public class RegisterModel implements Serializable {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("图片验证码")
    private String verifyCode;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

}
