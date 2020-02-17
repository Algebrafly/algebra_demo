package com.algebra.demofastdep.service;

import com.algebra.demofastdep.entity.domain.TUser;

import java.util.List;

/**
 * @author al
 * @date 2020/2/17 16:55
 * @description
 */
public interface IUserService {
    void saveUsers(List<TUser> users);
}
