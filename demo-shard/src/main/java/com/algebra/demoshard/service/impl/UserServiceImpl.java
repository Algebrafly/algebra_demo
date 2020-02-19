package com.algebra.demoshard.service.impl;

import com.algebra.demoshard.entity.domain.TUser;
import com.algebra.demoshard.mapper.TUserMapper;
import com.algebra.demoshard.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author al
 * @date 2020/2/17 16:56
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    TUserMapper userMapper;

    @Override
    public void saveUsers(List<TUser> users) {
        users.forEach(u -> {
            userMapper.insert(u);
        });
    }
}
