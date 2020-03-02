package com.algebra.demo.mapper;

import com.algebra.demo.entity.SysWebLog;
import org.apache.ibatis.annotations.Mapper;

/**
  * @author al
  * @date 2020/2/29 12:40
  * @description 
  */
@Mapper
public interface SysWebLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(SysWebLog record);

    int insertSelective(SysWebLog record);

    SysWebLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(SysWebLog record);

    int updateByPrimaryKey(SysWebLog record);
}