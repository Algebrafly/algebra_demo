package com.algebra.demoshard.service;

import com.algebra.demoshard.entity.domain.TableOne;

public interface ShardService {
    void createTable() ;
    void insertOne() ;
    TableOne selectOneByPhone(String phone) ;
}
