package com.algebra.authentication.service;

import com.algebra.authentication.domain.SysOrganization;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
public interface SysOrganizationService extends IService<SysOrganization> {


    List<SysOrganization> getOrganizationList();

    PageInfo<SysOrganization> getUserInfoPage(PageRequestParam param);

}
