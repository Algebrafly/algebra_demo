package com.algebra.authentication.web.excel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.algebra.authentication.domain.SysExcelHeader;
import com.algebra.authentication.domain.SysExcelModel;
import com.algebra.authentication.service.excel.SysExcelHeaderService;
import com.algebra.authentication.service.excel.SysExcelModelService;
import com.algebra.authentication.util.ExcelModelUtil;
import com.algebra.authentication.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wf.jwtp.annotation.Ignore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author al
 * @date 2020/7/30 17:33
 * @description
 */
@RestController
@Slf4j
@Api(value = "ExcelModel", tags = "Excel模板操作")
@Ignore
public class ExcelModelController {

    @Autowired
    ExcelModelUtil excelModelUtil;

    @Autowired
    SysExcelModelService excelModelService;

    @Autowired
    SysExcelHeaderService excelHeaderService;

    @ApiOperation("下载Excel模板-Version2(DataBase or OSS)")
    @GetMapping("/getExcelModeV2")
    public WebApiResult<String> getExcelModeV2(@RequestParam("businessKey") String businessKey, HttpServletResponse response) {
        log.info("下载Excel模版(DataBase or OSS)...");
        ExcelWriter writer = ExcelUtil.getWriter(true);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            log.info("根据业务主键获取模板信息");
            SysExcelModel model = excelModelService.getExcelModelByBusiness(businessKey);
            String excelName = model.getName()+"_"+model.getBusinessKey()+"_"+sdf.format(new Date());
            String storeType = model.getStoreType();
            if("2".equals(storeType)){
                log.info("OSS方式获取Excel模板----->>>");
                String ossUrl = model.getOssUrl();
                return WebApiResult.ok(ossUrl);
            }
            log.info("DB方式获取Excel模板----->>>");
            log.info("获取到Header及其别名");
            List<SysExcelHeader> headers = excelHeaderService.getHeadersByModel(model.getId());
            excelModelUtil.setHeaderAlias(writer, headers);

            log.info("检索字典下拉选数据");
            excelModelUtil.setSelectedData(writer,headers);

            log.info("填充示例数据");
            excelModelUtil.buildExampleData(writer,headers);

            String fileName = URLEncoder.encode(excelName + ".xlsx", "UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            ServletOutputStream out = response.getOutputStream();
            writer.flush(out, true);
            IoUtil.close(out);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Excel下载异常！");
            return WebApiResult.error("Error!");
        } finally {
            writer.close();
        }
    }


}
