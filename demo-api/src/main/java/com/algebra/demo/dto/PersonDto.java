package com.algebra.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/1/21 9:40
 * @description
 */
@Data
@ApiModel("person | 用户")
@Builder
public class PersonDto implements Serializable, Cloneable {

    @ApiModelProperty(value="姓名")
    private String name;

    @ApiModelProperty(value="密码")
    private String password;

    @ApiModelProperty(value="年龄")
    private Integer personAge;

    @ApiModelProperty(value="生日")
    private String personBirthday;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
