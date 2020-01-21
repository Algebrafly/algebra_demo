package com.algebra.demobase.service;

import com.algebra.demo.util.base.RequestPageUtil;
import com.algebra.demobase.entity.domain.Person;
import com.github.pagehelper.PageInfo;

/**
  * @author al
  * @date 2020/1/21 9:11
  * @description 
  */
public interface PersonService{


    int deleteByPrimaryKey(String personId);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String personId);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    PageInfo<Person> getPersonList(RequestPageUtil pageUtil);

}
