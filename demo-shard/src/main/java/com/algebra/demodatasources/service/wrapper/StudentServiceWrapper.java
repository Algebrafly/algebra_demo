package com.algebra.demodatasources.service.wrapper;

import com.algebra.demodatasources.entity.domain.Student;
import com.algebra.demodatasources.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
  * @author al
  * @date 2020/2/12 15:25
  * @description 
  */
@Component
public class StudentServiceWrapper {

    @Autowired
    StudentService studentService;

    public Student selectByPrimaryKey(String id) {
        return studentService.selectByPrimaryKey(id);
    }

}
