package com.algebra.demobase.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
  * @author al
  * @date 2021/3/2 10:49
  * @description 
  */
@ApiModel(value="com-algebra-demobase-entity-domain-Employee")
@Data
@TableName(value = "employee")
public class Employee implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="id")
    private Integer id;

    /**
     * 姓氏
     */
    @TableField(value = "first_name")
    @ApiModelProperty(value="姓氏")
    private String firstName;

    /**
     * 名字
     */
    @TableField(value = "last_name")
    @ApiModelProperty(value="名字")
    private String lastName;

    /**
     * 工资
     */
    @TableField(value = "salary")
    @ApiModelProperty(value="工资")
    private BigDecimal salary;

    /**
     * 关系人信息
     */
    @TableField(value = "relative_json")
    @ApiModelProperty(value="关系人信息")
    private String relativeJson;

    /**
     * 所欲机构ID
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value="所欲机构ID")
    private String deptId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_FIRST_NAME = "first_name";

    public static final String COL_LAST_NAME = "last_name";

    public static final String COL_SALARY = "salary";

    public static final String COL_RELATIVE_JSON = "relative_json";

    public static final String COL_DEPT_ID = "dept_id";
}