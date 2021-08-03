package com.algebra.authentication.mapper.config;

import com.algebra.authentication.domain.TmTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author al
 * @date 2021/7/13 14:41
 * @description
 */
@Mapper
public interface TmTestMapper extends BaseMapper<TmTest> {

    List<TmTest> getListForPage();

    List<String> getInParam(@Param("limit") int limit);

}
