package com.algebra.authentication.mapper.rbac;

import com.algebra.authentication.domain.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author al
 * @date 2020/7/9 10:37
 * @description
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    List<SysPermission> selectList();

    List<SysPermission> selectListByRole(String roleId);
}