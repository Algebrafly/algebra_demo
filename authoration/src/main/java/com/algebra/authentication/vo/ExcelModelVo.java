package com.algebra.authentication.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author al
 * @date 2020/8/18 16:40
 * @description
 */
@Data
@ApiModel("Excel模板")
public class ExcelModelVo implements Serializable {

    @ApiModelProperty(value = "ExcelModel主键")
    private String id;

    @ApiModelProperty(value = "模板名称")
    private String name;

    @ApiModelProperty(value = "模板编号")
    private String code;

    @ApiModelProperty(value = "关联的业务")
    private String businessKey;

    @ApiModelProperty(value = "应用ID，不传默认：default")
    private String appKey;

    @ApiModelProperty(value = "模板存储方式（1-DB，2-OSS）")
    private String storeType;

    @ApiModelProperty(value = "OSS云存储文档（当storeType=2时必传）")
    private String ossUrl;

    @ApiModelProperty(value = "模板表头集合")
    private List<ModelHeaderVo> headers;


}
