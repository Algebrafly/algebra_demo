package com.algebra.authentication.web;

import cn.hutool.json.JSONUtil;
import com.algebra.authentication.service.SysDictionaryService;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.vo.SelectedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2020/8/18 16:35
 * @description
 */
@Slf4j
@RestController
@Api(value = "dictionary", tags = "字典项")
public class DictionaryController {

    @Autowired
    SysDictionaryService dictionaryService;

    @ApiOperation("获取字典下拉选项（根据Type分组）")
    @GetMapping("/getSelectedDictionary")
    public WebApiResult<List<SelectedVo>> getSelectedDictionary(@RequestParam("type") String type){
        log.info("获取字典下拉选项（根据Type分组），接收到参数：{}", type);
        try {
            List<SelectedVo> selectedData = dictionaryService.getSelectedInfosByType(type);
            return WebApiResult.ok(selectedData);
        } catch (Exception e) {
            log.error("获取字典下拉选项异常，异常信息：{}", e.getMessage());
            return WebApiResult.error("获取失败！");
        }
    }

    @ApiOperation("批量获取字典下拉选项（type集合）")
    @PostMapping("/getSelectedDictionaryList")
    public WebApiResult<Map<String,List<SelectedVo>>> getSelectedDictionaryList(@RequestBody List<String> types){
        log.info("批量获取字典下拉选项（根据Type分组），接收到参数：{}", JSONUtil.toJsonStr(types));
        try {
            Map<String,List<SelectedVo>> resultMap = new HashMap<>();
            for (String type : types) {
                List<SelectedVo> selectedData = dictionaryService.getSelectedInfosByType(type);
                resultMap.put(type, selectedData);
            }
            return WebApiResult.ok(resultMap);
        } catch (Exception e) {
            log.error("批量获取字典下拉选项异常，异常信息：{}", e.getMessage());
            return WebApiResult.error("获取失败！");
        }
    }

}
