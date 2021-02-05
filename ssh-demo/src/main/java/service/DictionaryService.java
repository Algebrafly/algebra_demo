package service;

import entity.SysDictionaryEntity;

/**
 * @author al
 * @date 2021/2/5 11:12
 * @description
 */
public interface DictionaryService {

    SysDictionaryEntity selectByType(String type);

}
