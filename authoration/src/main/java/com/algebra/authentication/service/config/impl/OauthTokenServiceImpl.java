package com.algebra.authentication.service.config.impl;

import com.algebra.authentication.domain.OauthToken;
import com.algebra.authentication.mapper.config.OauthTokenMapper;
import com.algebra.authentication.service.config.OauthTokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author al
 * @date 2020/7/13 15:11
 * @description
 */
@Service
public class OauthTokenServiceImpl extends ServiceImpl<OauthTokenMapper, OauthToken> implements OauthTokenService {

    @Override
    public OauthToken getOneByToken(String token) {
        QueryWrapper<OauthToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OauthToken::getAccessToken, token);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean validTokenByDb(String token) {
        OauthToken oneByToken = this.getOneByToken(token);
        if (oneByToken == null) {
            return false;
        }
        long expireTime = oneByToken.getExpireTime().getTime();
        long nowTime = System.currentTimeMillis();
        if (nowTime > expireTime) {
            this.deleteByToken(token);
            return false;
        }
        return true;
    }

    @Override
    public void deleteByToken(String token) {
        QueryWrapper<OauthToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OauthToken::getAccessToken, token);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public String getTokenKey() {
        return baseMapper.getTokenKey();
    }
}

