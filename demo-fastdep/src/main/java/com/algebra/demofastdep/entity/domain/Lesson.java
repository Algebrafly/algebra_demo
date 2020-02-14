package com.algebra.demofastdep.entity.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
  * @author al
  * @date 2020/2/12 15:26
  * @description 
  */
@Data
public class Lesson implements Serializable {
    /**
    * 主键ID
    */
    private String id;

    /**
    * 课程名称
    */
    private String name;

    /**
    * 分数
    */
    private BigDecimal credit;

    /**
    * 授课老师
    */
    private String teacher;

    private static final long serialVersionUID = 1L;
}