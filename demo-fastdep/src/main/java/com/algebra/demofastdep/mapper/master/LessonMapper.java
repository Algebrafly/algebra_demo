package com.algebra.demofastdep.mapper.master;

import com.algebra.demofastdep.entity.domain.Lesson;
import org.apache.ibatis.annotations.Mapper;

/**
  * @author al
  * @date 2020/2/12 15:26
  * @description 
  */
@Mapper
public interface LessonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);
}