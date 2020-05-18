package com.algebra.demobase.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.algebra.demobase.mapper.AccountMsgMapper;
import com.algebra.demobase.entity.AccountMsg;
import com.algebra.demobase.service.AccountMsgService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author al
  * @date 2020/5/18 11:19
  * @description 
  */
@Service
public class AccountMsgServiceImpl implements AccountMsgService{

    @Resource
    private AccountMsgMapper accountMsgMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return accountMsgMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AccountMsg record) {
        return accountMsgMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int insertSelective(AccountMsg record) {
        return accountMsgMapper.insertSelective(record);
    }

    @Override
    public AccountMsg selectByPrimaryKey(Integer id) {
        return accountMsgMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AccountMsg record) {
        return accountMsgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AccountMsg record) {
        return accountMsgMapper.updateByPrimaryKey(record);
    }

}
