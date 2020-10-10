package com.algebra.authentication.service.excel.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.mapper.excel.SysModelHeaderMapper;
import com.algebra.authentication.domain.SysModelHeader;
import com.algebra.authentication.service.excel.SysModelHeaderService;

import java.util.List;

/**
 * @author al
 * @date 2020/10/10 10:47
 * @description
 */
@Service
public class SysModelHeaderServiceImpl extends ServiceImpl<SysModelHeaderMapper, SysModelHeader> implements SysModelHeaderService {
}

