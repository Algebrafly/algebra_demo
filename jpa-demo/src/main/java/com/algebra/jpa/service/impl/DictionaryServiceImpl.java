package com.algebra.jpa.service.impl;

import com.algebra.jpa.dao.DictionaryDao;
import com.algebra.jpa.entity.SysDictionary;
import com.algebra.jpa.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author al
 * @date 2021/2/5 11:39
 * @description
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    DictionaryDao dictionaryDao;

    @Override
    public void save(SysDictionary dictionary) {
        dictionaryDao.save(dictionary);
    }

    @Override
    public SysDictionary getOneByType(String type) {
        return null;
    }

    @Override
    public List<SysDictionary> getAll() {
        List<SysDictionary> al = new ArrayList<>();
        dictionaryDao.findAll().forEach(al::add);
        return al;
    }
}
