package com.algebra.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.algebra.demo.mapper.SysWebLogMapper;
import com.algebra.demo.entity.SysWebLog;
import com.algebra.demo.service.SysWebLogService;
/**
  * @author al
  * @date 2020/2/29 12:40
  * @description 
  */
@Service
public class SysWebLogServiceImpl implements SysWebLogService{

    @Resource
    private SysWebLogMapper sysWebLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer logId) {
        return sysWebLogMapper.deleteByPrimaryKey(logId);
    }

    @Override
    public int insert(SysWebLog record) {
        return sysWebLogMapper.insert(record);
    }

    @Override
    public int insertSelective(SysWebLog record) {
        return sysWebLogMapper.insertSelective(record);
    }

    @Override
    public SysWebLog selectByPrimaryKey(Integer logId) {
        return sysWebLogMapper.selectByPrimaryKey(logId);
    }

    @Override
    public int updateByPrimaryKeySelective(SysWebLog record) {
        return sysWebLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysWebLog record) {
        return sysWebLogMapper.updateByPrimaryKey(record);
    }

}
