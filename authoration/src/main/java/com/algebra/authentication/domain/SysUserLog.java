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
  * @date 2020/10/15 11:48
  * @description 
  */
@ApiModel(value="com-algebra-authentication-domain-SysUserLog")
@Data
@TableName(value = "sys_userlog")
public class SysUserLog implements Serializable {
    /**
     * 主键Id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    @ApiModelProperty(value="主键Id")
    private Long id;

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户Id")
    private String userId;

    /**
     * 用户名/昵称
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value="用户名/昵称")
    private String userName;

    /**
     * 类型
     */
    @TableField(value = "type")
    @ApiModelProperty(value="类型")
    private Short type;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="IP地址")
    private String ip;

    /**
     * 最新
     */
    @TableField(value = "is_new")
    @ApiModelProperty(value="最新")
    private Boolean isNew;

    /**
     * 内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="内容")
    private String content;

    /**
     * 菜单
     */
    @TableField(value = "menu")
    @ApiModelProperty(value="菜单")
    private String menu;

    /**
     * 机构ID
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value="机构ID")
    private String deptId;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    @ApiModelProperty(value="创建时间")
    private Date createdAt;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "Id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_TYPE = "type";

    public static final String COL_IP = "ip";

    public static final String COL_IS_NEW = "is_new";

    public static final String COL_CONTENT = "content";

    public static final String COL_MENU = "menu";

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_REMARK = "remark";

    public static final String COL_CREATED_AT = "created_at";
}