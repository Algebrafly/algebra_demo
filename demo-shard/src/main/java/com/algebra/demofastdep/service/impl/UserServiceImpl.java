package com.algebra.demofastdep.service.impl;

import com.algebra.demofastdep.entity.domain.TUser;
import com.algebra.demofastdep.mapper.TUserMapper;
import com.algebra.demofastdep.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author al
 * @date 2020/2/17 16:56
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    TUserMapper userMapper;

    @Override
    public void saveUsers(List<TUser> users) {
        users.forEach(u -> {
            userMapper.insertSelective(u);
        });
    }
}
