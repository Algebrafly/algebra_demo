package com.algebra.demofastdep.entity.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
  * @author al
  * @date 2020/2/12 15:26
  * @description 
  */
@ApiModel(value="课程")
@Data
public class Lesson implements Serializable {
    /**
    * 主键ID
    */
    @ApiModelProperty(value="主键ID")
    private String id;

    /**
    * 课程名称
    */
    @ApiModelProperty(value="课程名称")
    private String name;

    /**
    * 分数
    */
    @ApiModelProperty(value="分数")
    private BigDecimal credit;

    /**
    * 授课老师
    */
    @ApiModelProperty(value="授课老师")
    private String teacher;

    private static final long serialVersionUID = 1L;
}