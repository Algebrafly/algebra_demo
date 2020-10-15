package com.algebra.authentication.vo;

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
@ApiModel("注册参数")
public class RegisterModel implements Serializable {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("确认密码")
    private String confirmPassword;

    @ApiModelProperty("图片验证码（非必传）")
    private String verifyCode;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("手机验证码")
    private String mobileCode;

    @ApiModelProperty("邮箱（非必传）")
    private String email;

    @ApiModelProperty("客户真实姓名/昵称（非必传）")
    private String realName;


}
