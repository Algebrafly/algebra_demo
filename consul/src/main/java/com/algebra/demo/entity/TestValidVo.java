package com.algebra.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author al
 * @date 2022/2/17 9:44
 * @description
 */
@Data
@ApiModel(value = "测试参数校验")
public class TestValidVo {

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("开始日期")
    @Past(message = "开始日期不能大于当前日期")
    private Date start;

    @ApiModelProperty("数量")
    @Max(value = 100, message = "数量不能超出100")
    private Integer num;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("备注")
    @Length(min = 1, max = 100, message = "字符长度必须在1~100")
    private String mark;

    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$", message = "身份证号不合规")
    private String cardNo;

}
