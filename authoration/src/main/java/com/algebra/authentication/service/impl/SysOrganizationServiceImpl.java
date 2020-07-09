package com.algebra.authentication.service.impl;

import com.algebra.authentication.domain.SysOrganization;
import com.algebra.authentication.mapper.SysOrganizationMapper;
import com.algebra.authentication.service.SysOrganizationService;
import com.algebra.authentication.util.PageRequestParam;
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
public class SysOrganizationServiceImpl extends ServiceImpl<SysOrganizationMapper, SysOrganization> implements SysOrganizationService{

    @Override
    public List<SysOrganization> getOrganizationList() {
        return baseMapper.selectList();
    }

    @Override
    public PageInfo<SysOrganization> getUserInfoPage(PageRequestParam param) {
        if(param.getExtraParam() == null){
            param.setExtraParam(new HashMap<>());
        }
        int page = param.getCurrentPage();
        int pageSize = param.getPageSize();
        PageHelper.startPage(page,pageSize);
        List<SysOrganization> jobList = baseMapper.getUserInfoPage(param.getExtraParam());
        return new PageInfo<>(jobList);
    }
}
