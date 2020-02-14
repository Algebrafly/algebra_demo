package com.algebra.demofastdep.service.impl;

import com.algebra.demofastdep.entity.domain.Student;
import com.algebra.demofastdep.mapper.slave.StudentMapper;
import com.algebra.demofastdep.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public PageInfo<Student> getStudents(Map<String, Object> param) {
        List<Student> students = studentMapper.selectList(param);
        return new PageInfo<>(students);
    }

}
