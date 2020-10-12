package com.algebra.authentication.service.config.impl;

import com.algebra.authentication.vo.SelectedVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.algebra.authentication.domain.SysDictionary;
import com.algebra.authentication.mapper.config.SysDictionaryMapper;
import com.algebra.authentication.service.config.SysDictionaryService;

/**
 * @author al
 * @date 2020/8/18 15:10
 * @description
 */
@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryMapper, SysDictionary> implements SysDictionaryService {

    @Override
    public List<SelectedVo> getSelectedInfosByType(String type) {
        QueryWrapper<SysDictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysDictionary::getType, type);
        queryWrapper.lambda().eq(SysDictionary::getDeleted, false);

        List<SysDictionary> sysCodes = baseMapper.selectList(queryWrapper);
        List<SelectedVo> selectedVos = new ArrayList<>();

        if (sysCodes != null) {
            for (SysDictionary sysCode : sysCodes) {
                SelectedVo vo = new SelectedVo();
                vo.setValue(sysCode.getCode());
                vo.setLabel(sysCode.getContent());
                selectedVos.add(vo);
            }
            return selectedVos;
        }

        return null;
    }

    @Override
    public String getReturnMsg(String code, String languageType) {
        return baseMapper.getReturnMsg(code,languageType);
    }
}

