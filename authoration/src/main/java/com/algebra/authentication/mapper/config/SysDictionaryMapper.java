package com.algebra.authentication.mapper.config;

import com.algebra.authentication.domain.SysDictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author al
 * @date 2020/10/12 11:57
 * @description
 */
public interface SysDictionaryMapper extends BaseMapper<SysDictionary> {

    String getReturnMsg(@Param("code") String code, @Param("languageType") String languageType);

}