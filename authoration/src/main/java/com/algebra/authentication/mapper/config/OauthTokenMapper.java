package com.algebra.authentication.mapper.config;

import com.algebra.authentication.domain.OauthToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
  * @author al
  * @date 2020/10/15 14:53
  * @description 
  */
public interface OauthTokenMapper extends BaseMapper<OauthToken> {
    String getTokenKey();
}