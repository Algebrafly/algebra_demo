package com.algebra.authentication.service;

import com.algebra.authentication.domain.SysBranch;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
public interface SysBranchService extends IService<SysBranch> {

    List<SysBranch> getBranchList(String orgId);

    PageInfo<SysBranch> getBranchListPage(PageRequestParam pageRequestParam);

}
