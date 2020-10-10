package com.algebra.authentication.service.excel;

import com.algebra.authentication.domain.SysExcelHeader;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author al
 * @date 2020/10/10 10:47
 * @description
 */
public interface SysExcelHeaderService extends IService<SysExcelHeader> {

    List<SysExcelHeader> getHeadersByModel(String modelId);

}

