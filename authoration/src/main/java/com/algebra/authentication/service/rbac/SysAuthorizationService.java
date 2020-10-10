package com.algebra.authentication.service.rbac;

import com.algebra.authentication.domain.SysAuthorization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
public interface SysAuthorizationService extends IService<SysAuthorization> {

    List<SysAuthorization> getAllAuthorizations();

}
