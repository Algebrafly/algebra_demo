package com.algebra.authentication.service.excel;

import com.algebra.authentication.domain.SysExcelHeader;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author al
 * @date 2020/10/10 10:47
 * @description
 */
public interface SysExcelHeaderService extends IService<SysExcelHeader> {

    List<SysExcelHeader> getHeadersByModel(String modelId);

    PageInfo<SysExcelHeader> getHeadersForPage(PageRequestParam param);

    List<SysExcelHeader> getHeadersByIds(List<Integer> ids);
}


