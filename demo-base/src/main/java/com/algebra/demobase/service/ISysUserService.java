package com.algebra.demobase.service;

import com.algebra.demobase.entity.domain.SysUser;

import java.util.Map;

/**
  * @author al
  * @date 2020/2/5 11:10
  * @description 
  */
public interface ISysUserService{


    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getUserInfoOne(String userId,SysUser sysUser);

}
