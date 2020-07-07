package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
@Data
@TableName(value = "sys_authrozation")
public class SysAuthrozation implements Serializable {
    /**
     * 权限ID
     */
    @TableId(value = "auth_id", type = IdType.INPUT)
    private String authId;

    /**
     * 权限名称
     */
    @TableField(value = "auth_name")
    private String authName;

    /**
     * 权限代码
     */
    @TableField(value = "auth_code")
    private String authCode;

    /**
     * 权限描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "create_at")
    private Date createAt;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;

    public static final String COL_AUTH_ID = "auth_id";

    public static final String COL_AUTH_NAME = "auth_name";

    public static final String COL_AUTH_CODE = "auth_code";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATE_AT = "create_at";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_REMARK = "remark";
}