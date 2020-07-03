package com.algebra.authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author al
 * @date 2020/7/3 10:30
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String userId;

    private String userName;

    private String password;

}
