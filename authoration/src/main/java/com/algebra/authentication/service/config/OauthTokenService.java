package com.algebra.authentication.service.config;

import com.algebra.authentication.domain.OauthToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author al
 * @date 2020/7/13 15:11
 * @description
 */
public interface OauthTokenService extends IService<OauthToken> {

    OauthToken getOneByToken(String token);

    /**
     * 数据库验证Token有效期
     *
     * @param token
     * @return
     */
    boolean validTokenByDb(String token);

    void deleteByToken(String token);

    String getTokenKey();

}

