package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
@Data
@TableName(value = "sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "usr_id")
    private String usrId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USR_ID = "usr_id";

    public static final String COL_ROLE_ID = "role_id";
}