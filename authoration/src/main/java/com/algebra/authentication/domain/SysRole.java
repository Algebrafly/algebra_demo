package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author al
 * @date 2020/7/9 11:42
 * @description
 */
@Data
@TableName(value = "sys_role")
public class SysRole implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private String roleId;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色等级
     */
    @TableField(value = "role_level")
    private String roleLevel;

    /**
     * 头像
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 角色代码
     */
    @TableField(value = "role_code")
    private String roleCode;

    /**
     * 机构ID
     */
    @TableField(value = "org_id")
    private String orgId;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建用户id
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 更新用户id
     */
    @TableField(value = "update_by")
    private Long updateBy;

    private static final long serialVersionUID = 1L;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_ROLE_LEVEL = "role_level";

    public static final String COL_ICON = "icon";

    public static final String COL_ROLE_CODE = "role_code";

    public static final String COL_ORG_ID = "org_id";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_BY = "update_by";
}