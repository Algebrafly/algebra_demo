package com.algebra.demobase.mapper;

import com.algebra.demobase.entity.AccountMoney;

/**
  * @author al
  * @date 2020/5/18 11:18
  * @description 
  */
public interface AccountMoneyMapper {
    int deleteByPrimaryKey(String bizPk);

    int insert(AccountMoney record);

    int insertSelective(AccountMoney record);

    AccountMoney selectByPrimaryKey(String bizPk);

    int updateByPrimaryKeySelective(AccountMoney record);

    int updateByPrimaryKey(AccountMoney record);
}