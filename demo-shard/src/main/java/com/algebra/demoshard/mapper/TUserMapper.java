package com.algebra.demoshard.mapper;

import com.algebra.demoshard.entity.domain.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
  * @author al
  * @date 2020/2/17 16:54
  * @description 
  */
@Mapper
public interface TUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}