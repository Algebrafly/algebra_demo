package com.algebra.authentication.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/7/30 11:54
 * @description
 */
@Data
@ApiModel("下拉选项")
public class SelectedVo implements Serializable {

    @ApiModelProperty("description")
    private String label;

    @ApiModelProperty("code")
    private String value;

}
