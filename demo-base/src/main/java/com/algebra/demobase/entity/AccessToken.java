package com.algebra.demobase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author al
 * @date 2020/6/22 9:36
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {

    /** token */
    private String token;

    /** 失效时间 */
    private Date expireTime;

}
