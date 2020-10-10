package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author al
 * @date 2020/10/10 11:55
 * @description
 */
@Data
@TableName(value = "sys_excel_header")
public class SysExcelHeader implements Serializable {
    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * header名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * header别名
     */
    @TableField(value = "alias")
    private String alias;

    /**
     * 字段类型（1-默认普通字段；2-下拉选）
     */
    @TableField(value = "type")
    private String type;

    /**
     * 排序序号
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

    /**
     * 列宽
     */
    @TableField(value = "column_width")
    private Integer columnWidth;

    /**
     * 列填充颜色
     */
    @TableField(value = "column_color")
    private String columnColor;

    /**
     * 是否校验空值
     */
    @TableField(value = "check_none_value")
    private Boolean checkNoneValue;

    /**
     * 示例值
     */
    @TableField(value = "example_value")
    private String exampleValue;

    /**
     * 是否删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    @TableField(exist = false)
    private Integer columnIndex;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_ALIAS = "alias";

    public static final String COL_TYPE = "type";

    public static final String COL_SORT_ORDER = "sort_order";

    public static final String COL_COLUMN_WIDTH = "column_width";

    public static final String COL_COLUMN_COLOR = "column_color";

    public static final String COL_CHECK_NONE_VALUE = "check_none_value";

    public static final String COL_EXAMPLE_VALUE = "example_value";

    public static final String COL_DELETED = "deleted";
}