package com.algebra.authentication.mapper.config;

/**
 * @author al
 * @date 2021/1/19 9:30
 * @description
 */
public interface SysSequenceMapper {

    /**
     * 获取自定义主键
     * @param mark 主键标识
     * @return string类型主键
     */
    String getPrimaryKey(String mark);

    String getCurrVal(String mark);

    String getNextVal(String mark);

}
