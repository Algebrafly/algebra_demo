package com.algebra.demobase.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.algebra.demobase.mapper.AccountMoneyMapper;
import com.algebra.demobase.entity.AccountMoney;
import com.algebra.demobase.service.AccountMoneyService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author al
  * @date 2020/5/18 11:18
  * @description 
  */
@Service
public class AccountMoneyServiceImpl implements AccountMoneyService{

    @Resource
    private AccountMoneyMapper accountMoneyMapper;

    @Override
    public int deleteByPrimaryKey(String bizPk) {
        return accountMoneyMapper.deleteByPrimaryKey(bizPk);
    }

    @Override
    public int insert(AccountMoney record) {
        return accountMoneyMapper.insert(record);
    }

    @Override
    public int insertSelective(AccountMoney record) {
        return accountMoneyMapper.insertSelective(record);
    }

    @Override
    public AccountMoney selectByPrimaryKey(String bizPk) {
        return accountMoneyMapper.selectByPrimaryKey(bizPk);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int updateByPrimaryKeySelective(AccountMoney record) {

//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("类:AccountMoneyServiceImpl;方法:updateByPrimaryKeySelective;错误信息:"+e.getMessage());
//
//        }
        int a = 1/0;
        return accountMoneyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AccountMoney record) {
        return accountMoneyMapper.updateByPrimaryKey(record);
    }

}
