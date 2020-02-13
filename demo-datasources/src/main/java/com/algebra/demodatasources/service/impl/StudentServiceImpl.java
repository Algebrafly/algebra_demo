package com.algebra.demodatasources.service.impl;

import com.algebra.demodatasources.conf.db.druid.DataSourceConstants;
import com.algebra.demodatasources.conf.db.druid.DataSourceType;
import com.algebra.demodatasources.service.StudentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.algebra.demodatasources.entity.domain.Student;
import com.algebra.demodatasources.mapper.slave.StudentMapper;

/**
  * @author al
  * @date 2020/2/12 15:25
  * @description 
  */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }

}
