package com.algebra.authentication.service.excel.impl;

import com.algebra.authentication.domain.SysExcelHeader;
import com.algebra.authentication.util.CommonUtil;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.domain.SysExcelModel;
import com.algebra.authentication.mapper.excel.SysExcelModelMapper;
import com.algebra.authentication.service.excel.SysExcelModelService;

import java.util.HashMap;
import java.util.List;

/**
 * @author al
 * @date 2020/10/10 10:47
 * @description
 */
@Service
public class SysExcelModelServiceImpl extends ServiceImpl<SysExcelModelMapper, SysExcelModel> implements SysExcelModelService {

    @Override
    public SysExcelModel getExcelModelByBusiness(String bizKey) {
        QueryWrapper<SysExcelModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysExcelModel::getBusinessKey, bizKey);
        queryWrapper.lambda().eq(SysExcelModel::getDeleted, false);
        queryWrapper.lambda().eq(SysExcelModel::getAppKey, CommonUtil.getMdcKey("appKey"));
        queryWrapper.lambda().last("limit 1");
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public PageInfo<SysExcelModel> getExcelModelForPage(PageRequestParam param) {
        if(param.getExtraParam() == null){
            param.setExtraParam(new HashMap<>());
        }
        int page = param.getCurrentPage();
        int pageSize = param.getPageSize();
        PageHelper.startPage(page,pageSize);
        List<SysExcelModel> jobList = baseMapper.getModelsPage(param.getExtraParam());
        return new PageInfo<>(jobList);
    }
}



