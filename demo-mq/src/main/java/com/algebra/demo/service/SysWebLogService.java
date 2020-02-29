package com.algebra.demo.service;

import com.algebra.demo.entity.SysWebLog;
    /**
  * @author al
  * @date 2020/2/29 12:40
  * @description 
  */
public interface SysWebLogService{


    int deleteByPrimaryKey(Integer logId);

    int insert(SysWebLog record);

    int insertSelective(SysWebLog record);

    SysWebLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(SysWebLog record);

    int updateByPrimaryKey(SysWebLog record);

}
