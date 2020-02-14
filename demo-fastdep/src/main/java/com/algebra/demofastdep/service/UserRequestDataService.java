package com.algebra.demofastdep.service;

import com.algebra.demofastdep.entity.domain.UserRequestData;

import java.util.List;

/**
  * @author al
  * @date 2020/2/14 11:41
  * @description 
  */
public interface UserRequestDataService{


    int deleteByPrimaryKey(Long userId);

    int insert(UserRequestData record);

    int insertSelective(UserRequestData record);

    UserRequestData selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserRequestData record);

    int updateByPrimaryKey(UserRequestData record);

    List<UserRequestData> selectAll();

    List<UserRequestData> selectOptions();

}
