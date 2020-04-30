package com.algebra.demobase.service;

import com.algebra.demobase.entity.TransTest1;
    /**
  * @author al
  * @date 2020/4/30 11:49
  * @description 
  */
public interface TransTest1Service{


    int deleteByPrimaryKey(Integer sysId);

    int insert(TransTest1 record);

    int insertSelective(TransTest1 record);

    TransTest1 selectByPrimaryKey(Integer sysId);

    int updateByPrimaryKeySelective(TransTest1 record);

    int updateByPrimaryKey(TransTest1 record);

}
