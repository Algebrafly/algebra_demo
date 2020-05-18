package com.algebra.demobase.mapper;

import com.algebra.demobase.entity.AccountMsg;

/**
  * @author al
  * @date 2020/5/18 11:19
  * @description 
  */
public interface AccountMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountMsg record);

    int insertSelective(AccountMsg record);

    AccountMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountMsg record);

    int updateByPrimaryKey(AccountMsg record);
}