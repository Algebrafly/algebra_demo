package com.algebra.demofastdep.entity.domain;

import lombok.Data;

import java.io.Serializable;

/**
  * @author al
  * @date 2020/2/12 15:25
  * @description 
  */
@Data
public class Student implements Serializable {
    /**
    * 主键ID
    */
    private String id;

    /**
    * 姓名
    */
    private String name;

    /**
    * 年龄
    */
    private Integer age;

    private static final long serialVersionUID = 1L;
}