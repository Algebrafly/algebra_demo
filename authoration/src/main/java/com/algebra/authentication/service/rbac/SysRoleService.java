package com.algebra.authentication.service.rbac;

import com.algebra.authentication.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author al
 * @date 2020/7/7 11:01
 * @description
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getRoleInfoList(String orgId);

}


