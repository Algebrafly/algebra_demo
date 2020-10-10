package com.algebra.authentication.mapper.rbac;

import com.algebra.authentication.domain.SysOrganization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {
    List<SysOrganization> selectList();

    List<SysOrganization> getUserInfoPage(Map<String, Object> extraParam);
}