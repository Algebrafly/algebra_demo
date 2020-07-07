package com.algebra.authentication.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.mapper.SysRolePermissionMapper;
import com.algebra.authentication.domain.SysRolePermission;
import com.algebra.authentication.service.SysRolePermissionService;
/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService{

}
