package com.algebra.authentication.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/10/10 15:50
 * @description
 */
@ApiModel("模型关联表头集合")
@Data
public class ModelHeaderVo implements Serializable {

    @ApiModelProperty("表头字段ID")
    private Integer headerId;

    @ApiModelProperty("列索引（从 1 开始，1 代表第一列）")
    private Integer columnIndex;

}
