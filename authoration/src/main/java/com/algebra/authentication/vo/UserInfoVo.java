package com.algebra.authentication.vo;

import com.algebra.authentication.domain.SysAuthrozation;
import com.algebra.authentication.domain.SysPermission;
import com.algebra.authentication.domain.SysRole;
import com.algebra.authentication.domain.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author al
 * @date 2020/7/2 10:03
 * @description
 */
@Data
@ApiModel("登录后返回的用户信息")
public class UserInfoVo implements Serializable {

    @ApiModelProperty("用户基本信息 ")
    private SysUser user;

    @ApiModelProperty("角色信息")
    private List<SysRole> roles;

    @ApiModelProperty("用户可操作的菜单信息")
    private List<SysPermission> permissions;

    @ApiModelProperty("用户角色拥有的权限信息")
    private List<SysAuthrozation> authorizations;

    @ApiModelProperty("access_token")
    private String accessToken;

}
