package com.algebra.authentication.web;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.algebra.authentication.service.SysDictionaryService;
import com.algebra.authentication.util.WebApiResult;
import com.algebra.authentication.vo.ExcelModelForTestVo;
import com.algebra.authentication.vo.SelectedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author al
 * @date 2020/7/30 17:33
 * @description
 */
@RestController
@Slf4j
@Api(value = "SystemConfig", tags = "系统配置项")
public class SystemConfigController {


    @Autowired
    SysDictionaryService dictionaryService;


    @ApiOperation("下载Excel模板(NO DataBase version)")
    @GetMapping("/getExcelModel")
    public WebApiResult<String> getExcelModel(@RequestParam(value = "bizPk",required = false) String bizPk, HttpServletResponse response){
        log.info("下载Excel模版...");
        ExcelWriter writer = ExcelUtil.getWriter(true);
        try {
            // 模拟从Excel模版表获取到数据
            String excelName = "Excel_Model_v1.0";

            // 获取到的标题别名
            Map<String, String> headerAlias = getHeaderAlias();
//            headerAlias.forEach(writer::addHeaderAlias);
            setHeaderAlias(writer);
            writer.setOnlyAlias(true);
            // 设定列宽
            setColumnWidth(writer, headerAlias);

            Sheet sheet = writer.getSheet();
            DataValidationHelper helper = sheet.getDataValidationHelper();

            // 设置下拉选
            String[] vehicleModel = getVehicleModel();
            if(vehicleModel.length > 0){
                CellRangeAddressList addressList1 = new CellRangeAddressList(0, 500, 1, 1);
                DataValidationConstraint constraint1 = helper.createExplicitListConstraint(vehicleModel);
                DataValidation dataValidation1 = helper.createValidation(constraint1,addressList1);
                writer.addValidationData(dataValidation1);
            }

            String[] deviceModel = getDeviceModel();
            if(deviceModel.length > 0){
                CellRangeAddressList addressList2 = new CellRangeAddressList(0, 500, 4, 4);
                DataValidationConstraint constraint2 = helper.createExplicitListConstraint(deviceModel);
                DataValidation dataValidation2 = helper.createValidation(constraint2,addressList2);
                writer.addValidationData(dataValidation2);
            }


            List<ExcelModelForTestVo> rows = new ArrayList<>();
            // 示例数据
            rows.add(buildExampleData());

            writer.write(rows, true);

            String fileName = URLEncoder.encode(excelName + ".xlsx", "UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition","attachment;filename="+fileName);

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

    private String[] getDeviceModel(){
        List<SelectedVo> selectedVos = dictionaryService.getSelectedInfosByType("device_model");
        if(selectedVos != null){
            List<String> collect = selectedVos.stream().map(SelectedVo::getLabel).collect(Collectors.toList());
            return collect.toArray(new String[0]);
        }
        return new String[]{};
    }

    private String[] getVehicleModel(){
        List<SelectedVo> selectedVos = dictionaryService.getSelectedInfosByType("cehicle_model");
        if(selectedVos != null){
            List<String> collect = selectedVos.stream().map(SelectedVo::getLabel).collect(Collectors.toList());
            return collect.toArray(new String[0]);
        }
        return new String[]{};
    }

    private Map<String,String> getHeaderAlias() {
        // 模拟数据库模版表获取
        Map<String,String> header = new HashMap<>();
        header.put("vehicleSn","车辆SN");
        header.put("vehicleModel","车型");
        header.put("vinNumber","车架号");
        header.put("deviceNumber","设备号");
        header.put("deviceModelDesc","设备型号");
        header.put("bluetoothKey","蓝牙密钥");
        header.put("productionDate","生产日期");
        return header;
    }

    private void setHeaderAlias(ExcelWriter writer){
        writer.addHeaderAlias("vehicleSn","车辆SN")
                .addHeaderAlias("vehicleModel","车型")
                .addHeaderAlias("vinNumber","车架号")
                .addHeaderAlias("deviceNumber","设备号")
                .addHeaderAlias("deviceModelDesc","设备型号")
                .addHeaderAlias("bluetoothKey","蓝牙密钥")
                .addHeaderAlias("productionDate","生产日期");
    }

    private void setColumnWidth(ExcelWriter writer, Map<String,String> headers){
        for (int i = 0; i < headers.size(); i++) {
            writer.setColumnWidth(i,30);
        }
    }

    private ExcelModelForTestVo buildExampleData(){
        ExcelModelForTestVo excel = new ExcelModelForTestVo();
        excel.setVinNumber("example-1001");
        excel.setVehicleSn("example-1001");
        excel.setBluetoothKey("example-1");
        excel.setDeviceModelDesc("1");
        excel.setDeviceNumber("1");
        excel.setProductionDate(new Date());
        excel.setVehicleModel("1");
        return excel;
    }

}
