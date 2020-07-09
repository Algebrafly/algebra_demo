package com.algebra.authentication.mapper;

import com.algebra.authentication.domain.SysBranch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
public interface SysBranchMapper extends BaseMapper<SysBranch> {
    List<SysBranch> selectList();

    List<SysBranch> getUserInfoPage(Map<String, Object> extraParam);
}