package com.algebra.authentication.util;

import cn.hutool.poi.excel.ExcelWriter;
import com.algebra.authentication.domain.SysExcelHeader;
import com.algebra.authentication.service.config.SysDictionaryService;
import com.algebra.authentication.vo.SelectedVo;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author al
 * @date 2020/10/10 11:10
 * @description
 */
@Component
public class ExcelModelUtil {

    @Autowired
    SysDictionaryService dictionaryService;

    public Map<String, String> getHeaderAlias(List<SysExcelHeader> headers) {
        Map<String, String> headerMap = new HashMap<>();
        for (SysExcelHeader header : headers) {
            headerMap.put(header.getName(), header.getAlias());
        }
        return headerMap;
    }

    public void setHeaderAlias(ExcelWriter writer, List<SysExcelHeader> headers) {
        for (int i = 0; i < headers.size(); i++) {
            SysExcelHeader header = headers.get(i);
            // 设置表头名
            writer.addHeaderAlias(header.getName(), header.getAlias());
            if (header.getColumnWidth() != null) {
                // 设置列宽
                writer.setColumnWidth(i, header.getColumnWidth());
            } else {
                writer.autoSizeColumn(i);
            }
        }
        writer.setOnlyAlias(true);
    }

    public void setSelectedData(ExcelWriter writer, List<SysExcelHeader> headers) {
        Sheet sheet = writer.getSheet();
        DataValidationHelper helper = sheet.getDataValidationHelper();
        for (SysExcelHeader header : headers) {
            if("2".equals(header.getType())){
                // 设置下拉选
                String[] vehicleModel = this.getSelectedModel(header.getName());
                if (vehicleModel.length > 0) {
                    CellRangeAddressList addressList = new CellRangeAddressList(0, 500, header.getColumnIndex()-1, header.getColumnIndex()-1);
                    DataValidationConstraint constraint = helper.createExplicitListConstraint(vehicleModel);
                    DataValidation dataValidation1 = helper.createValidation(constraint, addressList);
                    writer.addValidationData(dataValidation1);
                }
            }
        }
    }

    public void buildExampleData(ExcelWriter writer, List<SysExcelHeader> headers) {
        List<Map<String,String>> rows = new ArrayList<>();
        Map<String, String> defaultMap = new HashMap<>();
        for (SysExcelHeader header : headers) {
            defaultMap.put(header.getName(), header.getExampleValue());
        }
        rows.add(defaultMap);
        writer.write(rows, true);
    }

    public String[] getSelectedModel(String type) {
        List<SelectedVo> selectedVos = dictionaryService.getSelectedInfosByType(type);
        if (selectedVos != null) {
            List<String> collect = selectedVos.stream().map(SelectedVo::getLabel).collect(Collectors.toList());
            return collect.toArray(new String[0]);
        }
        return new String[]{};
    }

}
