package com.algebra.demobase.service.impl;

import com.algebra.demo.util.base.RequestPageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.algebra.demobase.mapper.PersonMapper;
import com.algebra.demobase.entity.domain.Person;
import com.algebra.demobase.service.PersonService;

import java.util.HashMap;
import java.util.List;

/**
  * @author al
  * @date 2020/1/21 9:11
  * @description 
  */
@Service
public class PersonServiceImpl implements PersonService{

    @Resource
    private PersonMapper personMapper;

    @Override
    public int deleteByPrimaryKey(String personId) {
        return personMapper.deleteByPrimaryKey(personId);
    }

    @Override
    public int insert(Person record) {
        return personMapper.insert(record);
    }

    @Override
    public int insertSelective(Person record) {
        return personMapper.insertSelective(record);
    }

    @Override
    public Person selectByPrimaryKey(String personId) {
        return personMapper.selectByPrimaryKey(personId);
    }

    @Override
    public int updateByPrimaryKeySelective(Person record) {
        return personMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Person record) {
        return personMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Person> getPersonList(RequestPageUtil pageUtil) {
        if(pageUtil.getExtraParam() == null){
            pageUtil.setExtraParam(new HashMap<>());
        }
        int page = pageUtil.getCurrentPage();
        int pageSize = pageUtil.getPageSize();
        PageHelper.startPage(page,pageSize);
        List<Person> personList = personMapper.getPersonList(pageUtil.getExtraParam());
        return new PageInfo<>(personList);
    }

}
