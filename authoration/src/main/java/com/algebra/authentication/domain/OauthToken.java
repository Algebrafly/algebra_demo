package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
  * @author al
  * @date 2020/10/15 14:53
  * @description 
  */
@ApiModel(value="com-algebra-authentication-domain-OauthToken")
@Data
@TableName(value = "oauth_token")
public class OauthToken implements Serializable {
    @TableId(value = "token_id", type = IdType.AUTO)
    @ApiModelProperty(value="null")
    private Integer tokenId;

    @TableField(value = "user_id")
    @ApiModelProperty(value="null")
    private String userId;

    @TableField(value = "access_token")
    @ApiModelProperty(value="null")
    private String accessToken;

    @TableField(value = "refresh_token")
    @ApiModelProperty(value="null")
    private String refreshToken;

    @TableField(value = "expire_time")
    @ApiModelProperty(value="null")
    private Date expireTime;

    @TableField(value = "refresh_token_expire_time")
    @ApiModelProperty(value="null")
    private Date refreshTokenExpireTime;

    @TableField(value = "roles")
    @ApiModelProperty(value="null")
    private String roles;

    @TableField(value = "permissions")
    @ApiModelProperty(value="null")
    private String permissions;

    @TableField(value = "create_time")
    @ApiModelProperty(value="null")
    private Date createTime;

    @TableField(value = "update_time")
    @ApiModelProperty(value="null")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_TOKEN_ID = "token_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ACCESS_TOKEN = "access_token";

    public static final String COL_REFRESH_TOKEN = "refresh_token";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_REFRESH_TOKEN_EXPIRE_TIME = "refresh_token_expire_time";

    public static final String COL_ROLES = "roles";

    public static final String COL_PERMISSIONS = "permissions";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}