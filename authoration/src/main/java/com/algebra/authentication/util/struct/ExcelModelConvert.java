package com.algebra.authentication.util.struct;

import com.algebra.authentication.domain.SysExcelModel;
import com.algebra.authentication.domain.SysUser;
import com.algebra.authentication.vo.ExcelModelVo;
import com.algebra.authentication.vo.UserInfoDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author al
 * @date 2020/7/7 17:24
 * @description
 */
@Mapper(componentModel = "spring")
public interface ExcelModelConvert {

    SysExcelModel excelModelVoToDo(ExcelModelVo vo);

    List<SysExcelModel> excelModelVoToDos(List<ExcelModelVo> vo);

    ExcelModelVo excelModelDoToVo(SysExcelModel vo);

    List<ExcelModelVo> excelModelDoToVos(List<SysExcelModel> vo);

}
