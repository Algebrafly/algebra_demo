package com.algebra.authentication.service.excel;

import com.algebra.authentication.domain.SysExcelModel;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * @author al
 * @date 2020/10/10 10:47
 * @description
 */
public interface SysExcelModelService extends IService<SysExcelModel> {

    SysExcelModel getExcelModelByBusiness(String bizKey);

    PageInfo<SysExcelModel> getExcelModelForPage(PageRequestParam param);

}



