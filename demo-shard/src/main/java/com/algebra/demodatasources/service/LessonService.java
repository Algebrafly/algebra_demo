package com.algebra.demodatasources.service;

import com.algebra.demodatasources.entity.domain.Lesson;
    /**
  * @author al
  * @date 2020/2/12 15:26
  * @description 
  */
public interface LessonService{


    int deleteByPrimaryKey(String id);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);

}
