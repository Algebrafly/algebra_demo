package com.algebra.demobase.mapper;

import com.algebra.demobase.entity.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
  * @author al
  * @date 2021/3/2 10:49
  * @description 
  */
public interface EmployeeMapper extends BaseMapper<Employee> {

    Employee getOneByName(String name);

    List<Employee> getListByName(String name);

}