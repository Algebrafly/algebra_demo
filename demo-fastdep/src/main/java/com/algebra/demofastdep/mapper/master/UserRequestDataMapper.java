package com.algebra.demofastdep.mapper.master;

import com.algebra.demofastdep.entity.domain.UserRequestData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
  * @author al
  * @date 2020/2/14 11:41
  * @description 
  */
@Mapper
public interface UserRequestDataMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserRequestData record);

    int insertSelective(UserRequestData record);

    UserRequestData selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserRequestData record);

    int updateByPrimaryKey(UserRequestData record);

    List<UserRequestData> selectOptions();

    List<UserRequestData> selectAll();
}