package service;

import dao.SysDictionaryDao;
import entity.SysDictionaryEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author al
 * @date 2021/2/5 11:14
 * @description
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    SysDictionaryDao dictionaryDao;

    @Override
    public SysDictionaryEntity selectByType(String type) {
        return dictionaryDao.selectByType(type);
    }
}
