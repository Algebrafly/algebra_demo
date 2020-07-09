package com.algebra.authentication.service.impl;

import com.algebra.authentication.domain.SysBranch;
import com.algebra.authentication.mapper.SysBranchMapper;
import com.algebra.authentication.service.SysBranchService;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
@Service
public class SysBranchServiceImpl extends ServiceImpl<SysBranchMapper, SysBranch> implements SysBranchService{

    @Override
    public List<SysBranch> getBranchList(String orgId) {
        if(orgId == null){
            return baseMapper.selectList();
        } else {
            QueryWrapper<SysBranch> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SysBranch::getOrgId, orgId);
            return baseMapper.selectList(queryWrapper);
        }

    }

    @Override
    public PageInfo<SysBranch> getBranchListPage(PageRequestParam param) {
        if(param.getExtraParam() == null){
            param.setExtraParam(new HashMap<>());
        }
        int page = param.getCurrentPage();
        int pageSize = param.getPageSize();
        PageHelper.startPage(page,pageSize);
        List<SysBranch> jobList = baseMapper.getUserInfoPage(param.getExtraParam());
        return new PageInfo<>(jobList);
    }
}
