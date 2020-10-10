package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author al
 * @date 2020/10/10 11:22
 * @description
 */
@Data
@TableName(value = "sys_model_header")
public class SysModelHeader implements Serializable {
    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 模型主键
     */
    @TableField(value = "model_id")
    private String modelId;

    /**
     * header列主键
     */
    @TableField(value = "header_id")
    private Integer headerId;

    /**
     * header列下标（相同model下不允许重复）
     */
    @TableField(value = "column_index")
    private Integer columnIndex;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_MODEL_ID = "model_id";

    public static final String COL_HEADER_ID = "header_id";

    public static final String COL_COLUMN_INDEX = "column_index";
}