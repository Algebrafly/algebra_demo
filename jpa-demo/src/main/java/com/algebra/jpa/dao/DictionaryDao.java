package com.algebra.jpa.dao;

import com.algebra.jpa.entity.SysDictionary;
import org.springframework.data.repository.CrudRepository;

/**
 * @author al
 * @date 2021/2/5 11:37
 * @description
 */
public interface DictionaryDao extends CrudRepository<SysDictionary,Long> {
}
