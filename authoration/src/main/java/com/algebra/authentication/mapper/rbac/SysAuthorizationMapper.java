package com.algebra.authentication.mapper.rbac;

import com.algebra.authentication.domain.SysAuthorization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
public interface SysAuthorizationMapper extends BaseMapper<SysAuthorization> {
    List<SysAuthorization> selectList();
}