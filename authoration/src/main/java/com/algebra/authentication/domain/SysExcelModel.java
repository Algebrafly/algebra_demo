package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author al
 * @date 2020/10/10 15:41
 * @description
 */
@ApiModel(value = "Model")
@Data
@TableName(value = "sys_excel_model")
public class SysExcelModel implements Serializable {
    /**
     * ExcelModel主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "ExcelModel主键")
    private String id;

    /**
     * 模板名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "模板名称")
    private String name;

    /**
     * 模板编号
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "模板编号")
    private String code;

    /**
     * 关联的业务
     */
    @TableField(value = "business_key")
    @ApiModelProperty(value = "关联的业务")
    private String businessKey;

    /**
     * 应用ID
     */
    @TableField(value = "app_key")
    @ApiModelProperty(value = "应用ID")
    private String appKey;

    /**
     * 模板存储方式（1-DB，2-OSS）
     */
    @TableField(value = "store_type")
    @ApiModelProperty(value = "模板存储方式（1-DB，2-OSS）")
    private String storeType;

    /**
     * OSS云存储文档
     */
    @TableField(value = "oss_url")
    @ApiModelProperty(value = "OSS云存储文档")
    private String ossUrl;

    /**
     * 是否删除
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_CODE = "code";

    public static final String COL_BUSINESS_KEY = "business_key";

    public static final String COL_APP_KEY = "app_key";

    public static final String COL_STORE_TYPE = "store_type";

    public static final String COL_OSS_URL = "oss_url";

    public static final String COL_DELETED = "deleted";
}