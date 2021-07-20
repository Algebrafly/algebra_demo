package com.algebra.authentication.service.rbac.impl;

import com.algebra.authentication.domain.SysAuthorization;
import com.algebra.authentication.mapper.rbac.SysAuthorizationMapper;
import com.algebra.authentication.service.rbac.SysAuthorizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
@Service
public class SysAuthorizationServiceImpl extends ServiceImpl<SysAuthorizationMapper, SysAuthorization> implements SysAuthorizationService {

    @Override
    public List<SysAuthorization> getAllAuthorizations() {
        return baseMapper.selectList();
    }
}