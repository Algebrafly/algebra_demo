package com.algebra.authentication.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.mapper.SysUserRoleMapper;
import com.algebra.authentication.domain.SysUserRole;
import com.algebra.authentication.service.SysUserRoleService;
/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService{

}
