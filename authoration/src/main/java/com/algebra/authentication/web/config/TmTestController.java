package com.algebra.authentication.web.config;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.domain.TmTest;
import com.algebra.authentication.mapper.config.TmTestMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2021/7/13 14:57
 * @description
 */
@Slf4j
@RestController
@Api(tags = "TmTest")
public class TmTestController {

    @Autowired
    TmTestMapper testMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/testTm01")
    @ApiOperation("testTm01")
    public void testTm01(){

        QueryWrapper<TmTest> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().orderByAsc(TmTest::getInputTime);

        Page<TmTest> page = new Page<>(1,10);

        long start = System.currentTimeMillis();
        log.info("start: {}", start);
        Page<TmTest> listForPage = testMapper.selectPage(page, queryWrapper);
//        jdbcTemplate.queryForList("SELECT * FROM tm_aa LIMIT 10 OFFSET 1");
//        List<TmTest> listForPage = testMapper.getListForPage();

        long end = System.currentTimeMillis();
        log.info("end: {}", end);

        log.info("Cost time: {}", end-start);
//        System.out.println(JSONUtil.toJsonStr(listForPage));

    }

    @GetMapping("/testTm02")
    @ApiOperation("testTm02")
    public void testTm02(){

        long start = System.currentTimeMillis();
        log.info("start: {}", start);
        List<TmTest> listForPage = testMapper.getListForPage();

        long end = System.currentTimeMillis();
        log.info("end: {}", end);

        log.info("Cost time: {}", end-start);
        System.out.println(JSONUtil.toJsonStr(listForPage));

    }

}
