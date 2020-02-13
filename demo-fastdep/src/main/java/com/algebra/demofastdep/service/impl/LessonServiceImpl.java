package com.algebra.demofastdep.service.impl;

import com.algebra.demofastdep.entity.domain.Lesson;
import com.algebra.demofastdep.mapper.master.LessonMapper;
import com.algebra.demofastdep.service.LessonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
  * @author al
  * @date 2020/2/12 15:26
  * @description 
  */
@Service
public class LessonServiceImpl implements LessonService {

    @Resource
    private LessonMapper lessonMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return lessonMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Lesson record) {
        return lessonMapper.insert(record);
    }

    @Override
    public int insertSelective(Lesson record) {
        return lessonMapper.insertSelective(record);
    }

    @Override
    public Lesson selectByPrimaryKey(String id) {
        return lessonMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Lesson record) {
        return lessonMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Lesson record) {
        return lessonMapper.updateByPrimaryKey(record);
    }

}
