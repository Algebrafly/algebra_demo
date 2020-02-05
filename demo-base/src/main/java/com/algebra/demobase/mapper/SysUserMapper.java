package com.algebra.demobase.mapper;

import com.algebra.demobase.entity.domain.SysUser;

/**
  * @author al
  * @date 2020/2/5 11:10
  * @description 
  */
public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectUserOne(SysUser sysUser);
}