package com.algebra.authentication.mapper;

import com.algebra.authentication.domain.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author al
 * @date 2020/7/9 11:42
 * @description
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> getAllRoleInfos();
}