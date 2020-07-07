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
@TableName(value = "sys_branch")
public class SysBranch implements Serializable {
    /**
     * 部门ID
     */
    @TableId(value = "bran_id", type = IdType.INPUT)
    private String branId;

    /**
     * 部门名称
     */
    @TableField(value = "bran_name")
    private String branName;

    /**
     * 部门编码
     */
    @TableField(value = "bran_code")
    private String branCode;

    /**
     * 所属机构ID
     */
    @TableField(value = "org_id")
    private String orgId;

    /**
     * 部门描述
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

    public static final String COL_BRAN_ID = "bran_id";

    public static final String COL_BRAN_NAME = "bran_name";

    public static final String COL_BRAN_CODE = "bran_code";

    public static final String COL_ORG_ID = "org_id";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATE_AT = "create_at";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_REMARK = "remark";
}