package com.algebra.demobase.service.impl;

import com.algebra.demo.annotation.DoSomething;
import com.algebra.demobase.entity.domain.SysUser;
import com.algebra.demobase.mapper.SysUserMapper;
import com.algebra.demobase.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
  * @author al
  * @date 2020/2/5 11:10
  * @description 
  */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public int deleteByPrimaryKey(String userId) {
        return sysUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUser record) {
        return sysUserMapper.insertSelective(record);
    }

    @Override
    public SysUser selectByPrimaryKey(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    @DoSomething(key = "#userId",cacheName = "sys_user")
    public SysUser getUserInfoOne(String userId, SysUser sysUser) {

        return sysUserMapper.selectUserOne(sysUser);
    }

}
