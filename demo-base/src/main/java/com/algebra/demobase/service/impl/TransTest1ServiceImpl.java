package com.algebra.demobase.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.algebra.demobase.mapper.TransTest1Mapper;
import com.algebra.demobase.entity.TransTest1;
import com.algebra.demobase.service.TransTest1Service;
/**
  * @author al
  * @date 2020/4/30 11:49
  * @description 
  */
@Service
public class TransTest1ServiceImpl implements TransTest1Service{

    @Resource
    private TransTest1Mapper transTest1Mapper;

    @Override
    public int deleteByPrimaryKey(Integer sysId) {
        return transTest1Mapper.deleteByPrimaryKey(sysId);
    }

    @Override
    public int insert(TransTest1 record) {
        return transTest1Mapper.insert(record);
    }

    @Override
    public int insertSelective(TransTest1 record) {
        return transTest1Mapper.insertSelective(record);
    }

    @Override
    public TransTest1 selectByPrimaryKey(Integer sysId) {
        return transTest1Mapper.selectByPrimaryKey(sysId);
    }

    @Override
    public int updateByPrimaryKeySelective(TransTest1 record) {
        return transTest1Mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TransTest1 record) {
        return transTest1Mapper.updateByPrimaryKey(record);
    }

}
