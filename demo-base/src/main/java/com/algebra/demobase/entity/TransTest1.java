package com.algebra.demobase.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
  * @author al
  * @date 2020/4/30 11:49
  * @description 
  */
@Data
public class TransTest1 implements Serializable {
    /**
    * 自增主键
    */
    private Integer sysId;

    /**
    * 名称
    */
    private String transName;

    /**
    * 创建日期
    */
    private Date createDate;

    private static final long serialVersionUID = 1L;
}