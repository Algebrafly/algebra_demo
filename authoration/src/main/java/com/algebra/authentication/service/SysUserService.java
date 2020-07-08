package com.algebra.authentication.service;

import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.util.PageRequestParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
public interface SysUserService extends IService<SysUser>{

    SysUser getUserInfoByName(String name);

    SysUser getUserInfoByPwd(String name, String pwd);

    PageInfo<SysUser> getUserInfoPage(PageRequestParam param);

}
