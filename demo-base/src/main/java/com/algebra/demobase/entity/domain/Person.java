package com.algebra.demobase.entity.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
  * @author al
  * @date 2020/1/21 9:11
  * @description 
  */
@ApiModel(value="com-algebra-demobase-entity-domain-Person")
@Data
public class Person implements Serializable {
    /**
    * personId
    */
    @ApiModelProperty(value="personId")
    private String personId;

    /**
    * personName
    */
    @ApiModelProperty(value="personName")
    private String personName;

    /**
    * personAge
    */
    @ApiModelProperty(value="personAge")
    private Integer personAge;

    /**
    * personBirthday
    */
    @ApiModelProperty(value="personBirthday")
    private Date personBirthday;

    /**
    * remark
    */
    @ApiModelProperty(value="remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}