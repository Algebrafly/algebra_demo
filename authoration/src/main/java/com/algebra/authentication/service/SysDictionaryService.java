package com.algebra.authentication.service;

import com.algebra.authentication.domain.SysDictionary;
import com.algebra.authentication.vo.SelectedVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
  * @author al
  * @date 2020/8/18 15:10
  * @description 
  */
public interface SysDictionaryService extends IService<SysDictionary>{

    List<SelectedVo> getSelectedInfosByType(String type);

}
