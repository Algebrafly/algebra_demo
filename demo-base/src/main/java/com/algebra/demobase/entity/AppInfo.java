package com.algebra.demobase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author al
 * @date 2020/6/22 9:36
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {
    /** App id */
    private String appId;
    /** API 秘钥 */
    private String key;
}
