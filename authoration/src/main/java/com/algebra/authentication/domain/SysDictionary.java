package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
  * @author al
  * @date 2020/8/18 15:10
  * @description 
  */
@Data
@TableName(value = "sys_dictionary")
public class SysDictionary implements Serializable {
    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典目录/类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 字典code
     */
    @TableField(value = "code")
    private String code;

    /**
     * 字典内容（label）
     */
    @TableField(value = "content")
    private String content;

    /**
     * 附加字段
     */
    @TableField(value = "pattern")
    private String pattern;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 应用标识
     */
    @TableField(value = "app_key")
    private String appKey;

    /**
     * 是否删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TYPE = "type";

    public static final String COL_CODE = "code";

    public static final String COL_CONTENT = "content";

    public static final String COL_PATTERN = "pattern";

    public static final String COL_REMARK = "remark";

    public static final String COL_APP_KEY = "app_key";

    public static final String COL_DELETED = "deleted";
}