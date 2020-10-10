package com.algebra.authentication.mapper.excel;

import com.algebra.authentication.domain.SysExcelHeader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2020/10/10 15:42
 * @description
 */
public interface SysExcelHeaderMapper extends BaseMapper<SysExcelHeader> {

    List<SysExcelHeader> getHeadersByModel(String modelId);

    List<SysExcelHeader> getHeadersPage(Map<String, Object> param);
}