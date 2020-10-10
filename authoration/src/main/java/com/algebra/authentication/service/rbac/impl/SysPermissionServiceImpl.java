package com.algebra.authentication.service.rbac.impl;

import com.algebra.authentication.domain.SysPermission;
import com.algebra.authentication.mapper.rbac.SysPermissionMapper;
import com.algebra.authentication.service.rbac.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author al
 * @date 2020/7/7 11:02
 * @description
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<SysPermission> getAllMenus() {
        return baseMapper.selectList();
    }

    @Override
    public List<SysPermission> getMenusByRole(String roleId) {
        return baseMapper.selectListByRole(roleId);
    }
}

