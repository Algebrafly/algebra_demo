package com.algebra.authentication.mapper.excel;

import com.algebra.authentication.domain.SysExcelModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2020/10/10 15:41
 * @description
 */
public interface SysExcelModelMapper extends BaseMapper<SysExcelModel> {
    List<SysExcelModel> getModelsPage(Map<String, Object> param);
}