package com.algebra.authentication.service.config.impl;

import com.algebra.authentication.domain.SysUserLog;
import com.algebra.authentication.mapper.config.SysUserLogMapper;
import com.algebra.authentication.service.config.SysUserLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author al
 * @date 2020/10/15 11:44
 * @description
 */
@Service
public class SysUserLogServiceImpl extends ServiceImpl<SysUserLogMapper, SysUserLog> implements SysUserLogService {

}

