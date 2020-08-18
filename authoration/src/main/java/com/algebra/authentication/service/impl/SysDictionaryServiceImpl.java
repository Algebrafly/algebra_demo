package com.algebra.authentication.service.impl;

import com.algebra.authentication.vo.SelectedVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.domain.SysDictionary;
import com.algebra.authentication.mapper.SysDictionaryMapper;
import com.algebra.authentication.service.SysDictionaryService;
/**
  * @author al
  * @date 2020/8/18 15:10
  * @description 
  */
@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryMapper, SysDictionary> implements SysDictionaryService{

    @Override
    public List<SelectedVo> getSelectedInfosByType(String type) {
        QueryWrapper<SysDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysDictionary::getType, type);
        queryWrapper.lambda().eq(SysDictionary::getDeleted,false);

        List<SysDictionary> sysCodes = baseMapper.selectList(queryWrapper);
        List<SelectedVo> selectedVos = new ArrayList<>();

        if(sysCodes != null){
            String languageType = MDC.get("lang");
            for (SysDictionary sysCode : sysCodes) {
                SelectedVo vo = new SelectedVo();
                vo.setValue(sysCode.getCode());
                vo.setLabel(sysCode.getContent());
//                if(LanguageTypeEnum.CHINESE.getType().equals(languageType)){
//                    vo.setLabel(sysCode.getChinese());
//                } else if(LanguageTypeEnum.SPANISH.getType().equals(languageType)){
//                    vo.setLabel(sysCode.getSpanish());
//                } else {
//                    vo.setLabel(sysCode.getEnglish());
//                }
                selectedVos.add(vo);
            }
            return selectedVos;
        }

        return null;
    }
}
