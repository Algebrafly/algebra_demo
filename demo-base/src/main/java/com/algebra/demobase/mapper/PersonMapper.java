package com.algebra.demobase.mapper;

import com.algebra.demobase.entity.domain.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
  * @author al
  * @date 2020/1/21 9:11
  * @description 
  */
@Mapper
public interface PersonMapper {
    int deleteByPrimaryKey(String personId);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String personId);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    List<Person> getPersonList(Map<String,Object> param);
}