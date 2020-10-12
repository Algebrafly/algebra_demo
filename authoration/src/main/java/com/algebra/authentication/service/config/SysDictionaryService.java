package com.algebra.authentication.service.config;

import com.algebra.authentication.domain.SysDictionary;
import com.algebra.authentication.vo.SelectedVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author al
 * @date 2020/8/18 15:10
 * @description
 */
public interface SysDictionaryService extends IService<SysDictionary> {

    List<SelectedVo> getSelectedInfosByType(String type);

    /**
     * 根据语言类型和code查询不同语言的返回值
     * @param code code
     * @param languageType 语言类型
     * @return 语言
     */
    String getReturnMsg(String code, String languageType);

}

