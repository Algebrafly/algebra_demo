package com.algebra.authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/7/2 10:03
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo implements Serializable {

    private String userId;

    private String userName;

    private String password;


}
