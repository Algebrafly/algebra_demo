package com.algebra.demobase.service.impl;

import com.algebra.demobase.entity.domain.OprLog;
import com.algebra.demobase.mapper.OprLogMapper;
import com.algebra.demobase.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author al
 * @date 2019/6/10 19:47
 * @description
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    OprLogMapper oprLogMapper;

    @Override
    public void infoLog(String className, String methodName, String message, Date oprDate) {
        OprLog oprLog = new OprLog();
        oprLog.setLogTyp("1");
        oprLog.setOprClassName(className);
        oprLog.setOprMethodName(methodName);
        oprLog.setMessage(message);
        oprLog.setOprTime(oprDate);
        oprLogMapper.insertSelective(oprLog);
    }

    @Override
    public void errorLog(String className, String methodName, String message, Date oprDate) {
        OprLog oprLog = new OprLog();
        oprLog.setLogTyp("2");
        oprLog.setOprClassName(className);
        oprLog.setOprMethodName(methodName);
        oprLog.setMessage(message);
        oprLog.setOprTime(oprDate);
        oprLogMapper.insertSelective(oprLog);
    }
}
