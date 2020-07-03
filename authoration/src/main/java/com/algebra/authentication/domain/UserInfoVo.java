package com.algebra.authentication.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/7/2 10:03
 * @description
 */
@Data
public class UserInfoVo implements Serializable {

    private UserInfo userInfo;

    private String accessToken;

}
