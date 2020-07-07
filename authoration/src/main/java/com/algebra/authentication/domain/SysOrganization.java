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
@TableName(value = "sys_organization")
public class SysOrganization implements Serializable {
    /**
     * 机构ID
     */
    @TableId(value = "org_id", type = IdType.INPUT)
    private String orgId;

    /**
     * 机构名称
     */
    @TableField(value = "org_name")
    private String orgName;

    /**
     * 机构代码
     */
    @TableField(value = "org_code")
    private String orgCode;

    /**
     * 机构描述
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

    public static final String COL_ORG_ID = "org_id";

    public static final String COL_ORG_NAME = "org_name";

    public static final String COL_ORG_CODE = "org_code";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATE_AT = "create_at";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_REMARK = "remark";
}