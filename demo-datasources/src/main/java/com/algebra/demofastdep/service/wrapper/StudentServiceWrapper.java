package com.algebra.demofastdep.service.wrapper;

import com.algebra.demofastdep.conf.db.druid.DataSourceConstants;
import com.algebra.demofastdep.conf.db.druid.DataSourceType;
import com.algebra.demofastdep.entity.domain.Student;
import com.algebra.demofastdep.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
  * @author al
  * @date 2020/2/12 15:25
  * @description 
  */
@Component
@DataSourceType(value = DataSourceConstants.SLAVE)
public class StudentServiceWrapper {

    @Autowired
    StudentService studentService;

    public Student selectByPrimaryKey(String id) {
        return studentService.selectByPrimaryKey(id);
    }

}
