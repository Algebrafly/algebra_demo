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
 * @date 2020/10/12 11:57
 * @description
 */
@ApiModel(value = "com-algebra-authentication-domain-SysDictionary")
@Data
@TableName(value = "sys_dictionary")
public class SysDictionary implements Serializable {
    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增主键")
    private Long id;

    /**
     * 字典目录/类型
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "字典目录/类型")
    private String type;

    /**
     * 字典code
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "字典code")
    private String code;

    /**
     * 字典内容（label）
     */
    @TableField(value = "content")
    @ApiModelProperty(value = "字典内容（label）")
    private String content;

    /**
     * 字典内容（英文）
     */
    @TableField(value = "content_en")
    @ApiModelProperty(value = "字典内容（英文）")
    private String contentEn;

    /**
     * 字典内容（日语）
     */
    @TableField(value = "content_jp")
    @ApiModelProperty(value = "字典内容（日语）")
    private String contentJp;

    /**
     * 附加字段
     */
    @TableField(value = "pattern")
    @ApiModelProperty(value = "附加字段")
    private String pattern;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 应用标识
     */
    @TableField(value = "app_key")
    @ApiModelProperty(value = "应用标识")
    private String appKey;

    /**
     * 是否删除
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TYPE = "type";

    public static final String COL_CODE = "code";

    public static final String COL_CONTENT = "content";

    public static final String COL_CONTENT_EN = "content_en";

    public static final String COL_CONTENT_JP = "content_jp";

    public static final String COL_PATTERN = "pattern";

    public static final String COL_REMARK = "remark";

    public static final String COL_APP_KEY = "app_key";

    public static final String COL_DELETED = "deleted";
}