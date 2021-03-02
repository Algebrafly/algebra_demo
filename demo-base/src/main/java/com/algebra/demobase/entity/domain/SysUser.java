package com.algebra.demobase.entity.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
  * @author al
  * @date 2020/2/5 11:10
  * @description 
  */
@ApiModel(value="用户")
@Data
public class SysUser implements Serializable {
    /**
    * 用户ID
    */
    @ApiModelProperty(value="用户ID")
    private String userId;

    /**
    * 用户姓名
    */
    @ApiModelProperty(value="用户姓名")
    private String userName;

    /**
    * 登陆名
    */
    @ApiModelProperty(value="登陆名")
    private String loginName;

    /**
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String password;

    /**
    * 用户图标URL
    */
    @ApiModelProperty(value="用户图标URL")
    private String iconUrl;

    /**
    * 证件类型
    */
    @ApiModelProperty(value="证件类型")
    private String idTyp;

    /**
    * 证件号
    */
    @ApiModelProperty(value="证件号")
    private String idNum;

    /**
    * 手机号
    */
    @ApiModelProperty(value="手机号")
    private String phone;

    /**
    * 用户状态
    */
    @ApiModelProperty(value="用户状态")
    private String status;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}