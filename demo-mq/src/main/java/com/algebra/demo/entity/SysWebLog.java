package com.algebra.demo.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
  * @author al
  * @date 2020/2/29 12:40
  * @description 
  */
@Data
public class SysWebLog implements Serializable {
    /**
    * 自增log主键
    */
    private Integer logId;

    /**
    * 日志记录时间
    */
    private Date logDate;

    /**
    * 日志级别
    */
    private String logLevel;

    /**
    * 线程号
    */
    private String logThread;

    /**
     * 全局ID
     */
    private String traceId;

    /**
    * 类名
    */
    private String logClass;

    /**
    * 方法名
    */
    private String logMethod;

    /**
    * 影响行
    */
    private Integer logMethodLine;

    /**
    * 打印信息
    */
    private String logMsg;

    /**
    * 操作人
    */
    private String oprName;

    /**
    * 操作人ID
    */
    private String oprId;

    /**
    * 服务模块
    */
    private String serviceModel;

    public SysWebLog() {}

    public SysWebLog(String logMsg){
        this.logDate = new Date();
        this.logLevel = Thread.currentThread().getStackTrace()[2].getMethodName().toUpperCase();
        this.logThread = Thread.currentThread().getName();
        this.logClass = Thread.currentThread().getStackTrace()[3].getClassName();
        this.logMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
        this.logMethodLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
        this.logMsg = logMsg;
//        System.out.println(JSONObject.toJSONString(Thread.currentThread().getStackTrace()));
    }

    public SysWebLog(String logMsg, String oprName, String oprId, String serviceModel){
        this.logDate = new Date();
        this.logLevel = Thread.currentThread().getStackTrace()[2].getMethodName().toUpperCase();
        this.logThread = Thread.currentThread().getName();
        this.logClass = Thread.currentThread().getStackTrace()[3].getClassName();
        this.logMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
        this.logMethodLine = Thread.currentThread().getStackTrace()[3].getLineNumber();
        this.logMsg = logMsg;
        this.oprName = oprName;
        this.oprId =  oprId;
        this.serviceModel = serviceModel;
    }

    private static final long serialVersionUID = 1L;
}