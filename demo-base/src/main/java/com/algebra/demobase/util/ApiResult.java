package com.algebra.demobase.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author al
 * @date 2020/6/22 9:39
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {

    /** 代码 */
    private String code;

    /** 结果 */
    private String msg;

}
