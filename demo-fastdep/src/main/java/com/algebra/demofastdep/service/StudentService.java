package com.algebra.demofastdep.service;

import com.algebra.demofastdep.entity.domain.Student;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
  * @author al
  * @date 2020/2/12 15:25
  * @description 
  */
public interface StudentService{


    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    PageInfo<Student> getStudents(Map<String,Object> param);

}
