package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
  * @author al
  * @date 2020/7/7 11:02
  * @description 
  */
@Data
@TableName(value = "sys_permission")
public class SysPermission implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 权限名称
     */
    @TableField(value = "perm_name")
    private String permName;

    /**
     * 权限类型 0 - 系统接口; 1 - 菜单; 2 - 内置页面; 3 - 按钮; 4 - 数据权限
     */
    @TableField(value = "perm_type")
    private String permType;

    /**
     * 权限编码
     */
    @TableField(value = "perm_code")
    private String permCode;

    /**
     * 前端path
     */
    @TableField(value = "path")
    private String path;

    /**
     * 跳转url
     */
    @TableField(value = "redirect_url")
    private String redirectUrl;

    /**
     * 组件
     */
    @TableField(value = "component")
    private String component;

    /**
     * 图标样式
     */
    @TableField(value = "icon")
    private String icon;

    @TableField(value = "active_menu")
    private String activeMenu;

    /**
     * 上级id
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 排序优先级
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_PERM_NAME = "perm_name";

    public static final String COL_PERM_TYPE = "perm_type";

    public static final String COL_PERM_CODE = "perm_code";

    public static final String COL_PATH = "path";

    public static final String COL_REDIRECT_URL = "redirect_url";

    public static final String COL_COMPONENT = "component";

    public static final String COL_ICON = "icon";

    public static final String COL_ACTIVE_MENU = "active_menu";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_SORT_ORDER = "sort_order";

    public static final String COL_CREATE_TIME = "create_time";
}