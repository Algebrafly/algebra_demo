package com.algebra.demodatasources.service.wrapper;

import com.algebra.demodatasources.conf.db.druid.DataSourceConstants;
import com.algebra.demodatasources.conf.db.druid.DataSourceType;
import com.algebra.demodatasources.entity.domain.Lesson;
import com.algebra.demodatasources.mapper.master.LessonMapper;
import com.algebra.demodatasources.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
  * @author al
  * @date 2020/2/12 15:26
  * @description 
  */
@Component
@DataSourceType(value = DataSourceConstants.MASTER)
public class LessonServiceWrapper{

    @Autowired
    LessonService lessonService;

    public Lesson selectByPrimaryKey(String id) {
        return lessonService.selectByPrimaryKey(id);
    }

}
