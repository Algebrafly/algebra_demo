package com.algebra.demobase.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
  * @author al
  * @date 2020/5/18 11:19
  * @description 
  */
@Data
public class AccountMsg implements Serializable {
    /**
    * 自增主键
    */
    private Integer id;

    /**
    * 业务主键（关联外键）
    */
    private String bizPk;

    /**
    * 变更日期
    */
    private Date changeDate;

    /**
    * 变更备注
    */
    private String changeRemark;

    private static final long serialVersionUID = 1L;
}