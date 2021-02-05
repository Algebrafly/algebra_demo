package dao;

import entity.SysDictionaryEntity;

/**
 * @author al
 * @date 2021/2/5 10:49
 * @description
 */
public interface SysDictionaryDao {
    SysDictionaryEntity selectByType(String type);
}
