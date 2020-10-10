package com.algebra.authentication.service.excel.impl;

import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.domain.SysExcelHeader;
import com.algebra.authentication.mapper.excel.SysExcelHeaderMapper;
import com.algebra.authentication.service.excel.SysExcelHeaderService;

import java.util.HashMap;
import java.util.List;

/**
 * @author al
 * @date 2020/10/10 10:47
 * @description
 */
@Service
public class SysExcelHeaderServiceImpl extends ServiceImpl<SysExcelHeaderMapper, SysExcelHeader> implements SysExcelHeaderService {

    @Override
    public List<SysExcelHeader> getHeadersByModel(String modelId) {
        return baseMapper.getHeadersByModel(modelId);
    }

    @Override
    public PageInfo<SysExcelHeader> getHeadersForPage(PageRequestParam param) {
        if(param.getExtraParam() == null){
            param.setExtraParam(new HashMap<>());
        }
        int page = param.getCurrentPage();
        int pageSize = param.getPageSize();
        PageHelper.startPage(page,pageSize);
        List<SysExcelHeader> jobList = baseMapper.getHeadersPage(param.getExtraParam());
        return new PageInfo<>(jobList);
    }

    @Override
    public List<SysExcelHeader> getHeadersByIds(List<Integer> ids) {
        QueryWrapper<SysExcelHeader> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysExcelHeader::getDeleted ,false);
        queryWrapper.lambda().in(SysExcelHeader::getId, ids);
        return baseMapper.selectList(queryWrapper);
    }
}


