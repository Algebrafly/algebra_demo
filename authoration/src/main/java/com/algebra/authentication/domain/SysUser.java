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
  * @date 2020/7/7 11:01
  * @description 
  */
@Data
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    /**
     * 唯一主键
     */
    @TableId(value = "usr_id", type = IdType.INPUT)
    private String usrId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 账户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 头像
     */
    @TableField(value = "portrait")
    private String portrait;

    /**
     * 证件类型
     */
    @TableField(value = "idcard_typ")
    private String idcardTyp;

    /**
     * 证件号
     */
    @TableField(value = "idcard_no")
    private String idcardNo;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 所属机构ID
     */
    @TableField(value = "usr_branch")
    private String usrBranch;

    /**
     * 所属部门ID
     */
    @TableField(value = "usr_organization")
    private String usrOrganization;

    /**
     * 用户状态
     */
    @TableField(value = "usr_status")
    private String usrStatus;

    /**
     * 上级主管ID
     */
    @TableField(value = "usr_super")
    private String usrSuper;

    /**
     * 用户类型（1-后台管理者，2-C端用户）
     */
    @TableField(value = "user_typ")
    private String userTyp;

    /**
     * 特殊角色ID
     */
    @TableField(value = "special_role_id")
    private Integer specialRoleId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    /**
     * 最后登录IP
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;

    public static final String COL_USR_ID = "usr_id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_REAL_NAME = "real_name";

    public static final String COL_PORTRAIT = "portrait";

    public static final String COL_IDCARD_TYP = "idcard_typ";

    public static final String COL_IDCARD_NO = "idcard_no";

    public static final String COL_PHONE = "phone";

    public static final String COL_EMAIL = "email";

    public static final String COL_USR_BRANCH = "usr_branch";

    public static final String COL_USR_ORGANIZATION = "usr_organization";

    public static final String COL_USR_STATUS = "usr_status";

    public static final String COL_USR_SUPER = "usr_super";

    public static final String COL_SPECIAL_ROLE_ID = "special_role_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_LOGIN_TIME = "login_time";

    public static final String COL_LOGIN_IP = "login_ip";

    public static final String COL_REMARK = "remark";
}