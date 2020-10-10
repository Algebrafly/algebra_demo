package com.algebra.authentication.service.excel.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.domain.SysExcelHeader;
import com.algebra.authentication.mapper.excel.SysExcelHeaderMapper;
import com.algebra.authentication.service.excel.SysExcelHeaderService;

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
}

