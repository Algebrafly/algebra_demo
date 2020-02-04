package com.algebra.demobase.mapper;

import com.algebra.demobase.entity.domain.OprLog;

public interface OprLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OprLog record);

    int insertSelective(OprLog record);

    OprLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OprLog record);

    int updateByPrimaryKey(OprLog record);
}