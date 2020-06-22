package com.algebra.demobase.entity;

import lombok.Data;

/**
 * @author al
 * @date 2020/6/22 9:37
 * @description
 */
@Data
public class TokenInfo {

    /** token类型: api:0 、user:1 */
    private Integer tokenType;

    /** App 信息 */
    private AppInfo appInfo;

    /** 用户其他数据 */
    private UserInfo userInfo;

}
