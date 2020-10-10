package com.algebra.authentication.web.excel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.algebra.authentication.domain.SysExcelHeader;
import com.algebra.authentication.domain.SysExcelModel;
import com.algebra.authentication.domain.SysModelHeader;
import com.algebra.authentication.service.excel.SysExcelHeaderService;
import com.algebra.authentication.service.excel.SysExcelModelService;
import com.algebra.authentication.service.excel.SysModelHeaderService;
import com.algebra.authentication.util.CommonUtil;
import com.algebra.authentication.util.ExcelModelUtil;
import com.algebra.authentication.util.PageRequestParam;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.util.struct.ExcelModelConvert;
import com.algebra.authentication.vo.ExcelModelVo;
import com.algebra.authentication.vo.ModelHeaderVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wf.jwtp.annotation.Ignore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    SysModelHeaderService modelHeaderService;

    @Autowired
    ExcelModelConvert excelModelConvert;

    @ApiOperation("下载Excel模板-Version2(DataBase or OSS)")
    @GetMapping("/getExcelModeV2")
    public WebApiResult<String> getExcelModeV2(@RequestParam("businessKey") String businessKey, HttpServletResponse response) {
        log.info("下载Excel模版(DataBase or OSS)...");
        ExcelWriter writer = ExcelUtil.getWriter(true);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            log.info("根据业务主键获取模板信息");
            SysExcelModel model = excelModelService.getExcelModelByBusiness(businessKey);
            String excelName = model.getName() + "_" + model.getBusinessKey() + "_" + sdf.format(new Date());
            String storeType = model.getStoreType();
            if ("2".equals(storeType)) {
                log.info("OSS方式获取Excel模板----->>>");
                String ossUrl = model.getOssUrl();
                return WebApiResult.ok(ossUrl);
            }
            log.info("DB方式获取Excel模板----->>>");
            log.info("获取到Header及其别名");
            List<SysExcelHeader> headers = excelHeaderService.getHeadersByModel(model.getId());
            excelModelUtil.setHeaderAlias(writer, headers);

            log.info("检索字典下拉选数据");
            excelModelUtil.setSelectedData(writer, headers);

            log.info("填充示例数据");
            excelModelUtil.buildExampleData(writer, headers);

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

    // <editor-fold description="管理运维">

    @PostMapping("/addExcelHeader")
    @ApiOperation("新增Excel表头字段")
    public WebApiResult<Void> addExcelHeader(@RequestBody SysExcelHeader excelHeader) {
        log.info("新增Excel表头字段，接收到参数：{}", JSONUtil.toJsonStr(excelHeader));
        try {
            excelHeaderService.save(excelHeader);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增Excel表头字段异常，信息：{}", e.getMessage());
            return WebApiResult.error(e.getMessage());
        }
        return WebApiResult.ok("新增成功！");
    }

    @PostMapping("/addExcelModel")
    @ApiOperation("新增Excel模板")
    public WebApiResult<Void> addExcelModel(@RequestBody ExcelModelVo excelModelVo) {
        log.info("新增Excel模板，接收到参数：{}", JSONUtil.toJsonStr(excelModelVo));
        try {
            List<ModelHeaderVo> headers = excelModelVo.getHeaders();
            if(headers == null || headers.size() == 0){
                return WebApiResult.error("请选择表头字段！");
            }
            String modelId = CommonUtil.createPrimaryKey("Model");
            SysExcelModel sysExcelModel = excelModelConvert.excelModelVoToDo(excelModelVo);
            sysExcelModel.setId(modelId);
            excelModelService.save(sysExcelModel);

            List<SysModelHeader> modelHeaders = new ArrayList<>();
            for (ModelHeaderVo header : headers) {
                SysModelHeader modelHeader = new SysModelHeader();
                modelHeader.setModelId(modelId);
                modelHeader.setHeaderId(header.getHeaderId());
                modelHeader.setColumnIndex(header.getColumnIndex());
                modelHeaders.add(modelHeader);
            }
            modelHeaderService.saveBatch(modelHeaders);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增Excel模板异常，信息：{}", e.getMessage());
            return WebApiResult.error(e.getMessage());
        }
        return WebApiResult.ok("新增成功！");
    }

    @ApiOperation("查询表头字段列表（分页）")
    @PostMapping("/getExcelHeadersForPage")
    public WebApiResult<List<SysExcelHeader>> getExcelHeadersForPage(@RequestBody PageRequestParam pageRequestParam){
        log.info("查询表头字段列表（分页），接收到参数：{}", JSONUtil.toJsonStr(pageRequestParam));
        try {
            PageInfo<SysExcelHeader> pageInfo = excelHeaderService.getHeadersForPage(pageRequestParam);

            return WebApiResult.ok(pageInfo.getList(), (int) pageInfo.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询表头字段列表（分页）异常，信息：{}", e.getMessage());
            return WebApiResult.error(e.getMessage());
        }

    }

    @ApiOperation("查询Excel模型列表（分页）")
    @PostMapping("/getExcelModelForPage")
    public WebApiResult<List<ExcelModelVo>> getExcelModelForPage(@RequestBody PageRequestParam pageRequestParam){
        log.info("查询Excel模型列表（分页），接收到参数：{}", JSONUtil.toJsonStr(pageRequestParam));
        try {
            PageInfo<SysExcelModel> pageInfo = excelModelService.getExcelModelForPage(pageRequestParam);
            List<SysExcelModel> list = pageInfo.getList();
            List<ExcelModelVo> excelModelVos = excelModelConvert.excelModelDoToVos(list);
            for (ExcelModelVo excelModelVo : excelModelVos) {
                String modelVoId = excelModelVo.getId();
                List<SysModelHeader> modelHeaders = modelHeaderService.getModelHeaderByModel(modelVoId);
                List<ModelHeaderVo> modelHeaderVos = new ArrayList<>();
                for (SysModelHeader modelHeader : modelHeaders) {
                    ModelHeaderVo modelHeaderVo = new ModelHeaderVo();
                    modelHeaderVo.setHeaderId(modelHeader.getHeaderId());
                    modelHeaderVo.setColumnIndex(modelHeader.getColumnIndex());
                    modelHeaderVos.add(modelHeaderVo);
                }
                excelModelVo.setHeaders(modelHeaderVos);
            }

            return WebApiResult.ok(excelModelVos,(int) pageInfo.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询Excel模型列表（分页）异常，信息：{}", e.getMessage());
            return WebApiResult.error(e.getMessage());
        }
    }

    @ApiOperation("查询表头字段列表（根据Id集合）")
    @PostMapping("/getExcelHeadersByIds")
    public WebApiResult<List<SysExcelHeader>> getExcelHeadersByIds(@RequestBody List<Integer> headerIds){
        log.info("查询表头字段列表（分页），接收到参数：{}", JSONUtil.toJsonStr(headerIds));
        try {
            List<SysExcelHeader> headersByIds = excelHeaderService.getHeadersByIds(headerIds);
            return WebApiResult.ok(headersByIds);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询表头字段列表（分页）异常，信息：{}", e.getMessage());
            return WebApiResult.error(e.getMessage());
        }

    }

    // </editor-fold>

}
