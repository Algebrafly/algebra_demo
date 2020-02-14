package com.algebra.demofastdep.conf;

import com.algebra.demofastdep.mapper.master.UserRequestDataMapper;
import com.louislivi.fastdep.shirojwt.shiro.FastDepShiroJwtAuthorization;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * FastDepShiroJwtConfig
 *
 * @author : louislivi
 */
@Component
public class FastDepShiroJwtConfig extends FastDepShiroJwtAuthorization {

    @Autowired
    private UserRequestDataMapper userRequestDataMapper;

    @Override
    public SimpleAuthorizationInfo getAuthorizationInfo(String userId) {
        Set<String> collect = userRequestDataMapper.selectOptions().stream().map(u -> u.getUserId().toString()).collect(Collectors.toSet());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        System.out.println(collect);
        // 当前值为 [1]
        simpleAuthorizationInfo.addStringPermissions(collect);
        return simpleAuthorizationInfo;
    }
}
