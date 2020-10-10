package com.algebra.authentication.service.rbac;

import com.algebra.authentication.domain.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author al
 * @date 2020/7/7 11:02
 * @description
 */
public interface SysPermissionService extends IService<SysPermission> {


    List<SysPermission> getAllMenus();

    List<SysPermission> getMenusByRole(String roleId);

}

