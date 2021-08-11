package com.algebra.authentication.web;

import com.algebra.authentication.mapper.config.SysSequenceMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2021/8/11 17:04
 * @description
 */
@RestController
@Api(tags = "测试序列获取")
public class TestSequenceController {

    @Autowired
    SysSequenceMapper sequenceMapper;

    @ApiOperation("getPrimaryKey")
    @GetMapping("/getPrimaryKey")
    public String getPrimaryKey(@RequestParam("mark") String mark) {
        return sequenceMapper.getPrimaryKey(mark);
    }

    @ApiOperation("getPrimaryKeyForSkip")
    @GetMapping("/getPrimaryKeyForSkip")
    public String getPrimaryKeyForSkip(@RequestParam("mark") String mark) {
        sequenceMapper.getNextVal(mark);
        String currVal = sequenceMapper.getCurrVal(mark);
        String nextVal = sequenceMapper.getNextVal(mark);
        while (true) {
            if (currVal.equals(nextVal)) {
                nextVal = sequenceMapper.getNextVal(mark);
            } else {
                break;
            }
        }
        return mark + nextVal;
    }

}
