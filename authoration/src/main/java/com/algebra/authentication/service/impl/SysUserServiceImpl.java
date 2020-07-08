package com.algebra.authentication.service.impl;

import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.mapper.SysUserMapper;
import com.algebra.authentication.service.SysUserService;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Override
    public SysUser getUserInfoByName(String name) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,name);
        return this.getOne(queryWrapper);
    }

    @Override
    public SysUser getUserInfoByPwd(String name, String pwd) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,name);
        queryWrapper.lambda().eq(SysUser::getPassword,pwd);
        return this.getOne(queryWrapper);
    }

    @Override
    public PageInfo<SysUser> getUserInfoPage(PageRequestParam param) {
        if(param.getExtraParam() == null){
            param.setExtraParam(new HashMap<>());
        }
        int page = param.getCurrentPage();
        int pageSize = param.getPageSize();
        PageHelper.startPage(page,pageSize);
        List<SysUser> jobList = baseMapper.getUserInfoPage(param.getExtraParam());
        return new PageInfo<>(jobList);
    }
}
