package com.algebra.demobase.entity.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author al
 * @date 2019/8/22 16:04
 * @description 用户登录信息实体
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("用户操作类DO")
public class User {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "图标链接")
    private String iconUrl;

    @ApiModelProperty(value = "用户证件类型")
    private String idTyp;

    @ApiModelProperty(value = "用户证件号")
    private String idNum;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户状态")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;


}
