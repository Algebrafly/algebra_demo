package com.algebra.demobase.service;

import com.algebra.demobase.entity.domain.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
  * @author al
  * @date 2021/3/2 10:49
  * @description 
  */
public interface EmployeeService extends IService<Employee>{

    Employee getOneByName(String name);

    List<Employee> getListByName(String name);

    List<Employee> getListByName2(String name);

}
