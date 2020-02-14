package com.algebra.demofastdep.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.algebra.demofastdep.entity.domain.UserRequestData;
import com.algebra.demofastdep.mapper.master.UserRequestDataMapper;
import com.algebra.demofastdep.service.UserRequestDataService;

import java.util.List;

/**
  * @author al
  * @date 2020/2/14 11:41
  * @description 
  */
@Service
public class UserRequestDataServiceImpl implements UserRequestDataService{

    @Resource
    private UserRequestDataMapper userRequestDataMapper;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userRequestDataMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(UserRequestData record) {
        return userRequestDataMapper.insert(record);
    }

    @Override
    public int insertSelective(UserRequestData record) {
        return userRequestDataMapper.insertSelective(record);
    }

    @Override
    public UserRequestData selectByPrimaryKey(Long userId) {
        return userRequestDataMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserRequestData record) {
        return userRequestDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserRequestData record) {
        return userRequestDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserRequestData> selectAll() {
        return userRequestDataMapper.selectAll();
    }

    @Override
    public List<UserRequestData> selectOptions() {
        return userRequestDataMapper.selectOptions();
    }

}
