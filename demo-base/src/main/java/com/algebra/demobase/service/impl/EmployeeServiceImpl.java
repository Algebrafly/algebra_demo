package com.algebra.demobase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.demobase.entity.domain.Employee;
import com.algebra.demobase.mapper.EmployeeMapper;
import com.algebra.demobase.service.EmployeeService;

import java.util.List;

/**
 * @author al
 * @date 2021/3/2 10:49
 * @description
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public Employee getOneByName(String name) {
        return baseMapper.getOneByName(name);
    }

    @Override
    public List<Employee> getListByName(String name) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Employee::getFirstName, name);
        return this.list(wrapper);
    }

    @Override
    public List<Employee> getListByName2(String name) {
        return null;
    }
}
