package com.algebra.jpa.service;

import com.algebra.jpa.entity.SysDictionary;

import java.util.List;

/**
 * @author al
 * @date 2021/2/5 11:39
 * @description
 */
public interface DictionaryService {

    void save(SysDictionary dictionary);

    SysDictionary getOneByType(String type);

    List<SysDictionary> getAll();

}
