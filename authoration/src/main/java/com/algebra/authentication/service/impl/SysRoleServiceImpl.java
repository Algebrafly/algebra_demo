package com.algebra.authentication.service.impl;

import com.algebra.authentication.domain.SysRole;
import com.algebra.authentication.mapper.SysRoleMapper;
import com.algebra.authentication.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author al
 * @date 2020/7/7 11:01
 * @description
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> getRoleInfoList(String orgId) {
        if (orgId == null) {
            return baseMapper.getAllRoleInfos();
        }
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRole::getOrgId, orgId);
        return baseMapper.selectList(queryWrapper);
    }
}


