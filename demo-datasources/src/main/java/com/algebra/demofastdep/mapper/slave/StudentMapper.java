package com.algebra.demofastdep.mapper.slave;

import com.algebra.demofastdep.entity.domain.Student;
import org.apache.ibatis.annotations.Mapper;

/**
  * @author al
  * @date 2020/2/12 15:25
  * @description 
  */
@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}