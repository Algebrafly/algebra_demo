package com.algebra.demofastdep.service.wrapper;

import com.algebra.demofastdep.conf.db.druid.DataSourceConstants;
import com.algebra.demofastdep.conf.db.druid.DataSourceType;
import com.algebra.demofastdep.entity.domain.Lesson;
import com.algebra.demofastdep.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
