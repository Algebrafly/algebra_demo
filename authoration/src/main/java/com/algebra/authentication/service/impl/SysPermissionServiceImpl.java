package com.algebra.authentication.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.mapper.SysPermissionMapper;
import com.algebra.authentication.domain.SysPermission;
import com.algebra.authentication.service.SysPermissionService;
/**
  * @author al
  * @date 2020/7/7 11:02
  * @description 
  */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService{

}
