package com.algebra.demobase.service;

import com.algebra.demobase.entity.AccountMoney;
    /**
  * @author al
  * @date 2020/5/18 11:18
  * @description 
  */
public interface AccountMoneyService{


    int deleteByPrimaryKey(String bizPk);

    int insert(AccountMoney record);

    int insertSelective(AccountMoney record);

    AccountMoney selectByPrimaryKey(String bizPk);

    int updateByPrimaryKeySelective(AccountMoney record);

    int updateByPrimaryKey(AccountMoney record);

}
