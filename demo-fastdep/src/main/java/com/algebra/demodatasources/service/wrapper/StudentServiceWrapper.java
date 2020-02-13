package com.algebra.demodatasources.service.wrapper;

import com.algebra.demodatasources.conf.db.druid.DataSourceConstants;
import com.algebra.demodatasources.conf.db.druid.DataSourceType;
import com.algebra.demodatasources.entity.domain.Student;
import com.algebra.demodatasources.mapper.slave.StudentMapper;
import com.algebra.demodatasources.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
