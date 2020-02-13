package com.algebra.demofastdep.entity.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
  * @author al
  * @date 2020/2/12 15:25
  * @description 
  */
@ApiModel(value="学生")
@Data
public class Student implements Serializable {
    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID")
    private String id;

    /**
    * 姓名
    */
    @ApiModelProperty(value="姓名")
    private String name;

    /**
    * 年龄
    */
    @ApiModelProperty(value="年龄")
    private Integer age;

    private static final long serialVersionUID = 1L;
}