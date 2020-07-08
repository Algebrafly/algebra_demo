package com.algebra.authentication.util;
/**
 * @version V1.0
 * @date 2020-7-01
 * @author algebra
 */
public class PageUtil {
    public static Integer getPageNo(Integer limit,Integer offset){
        return offset==0 ? 1 : offset / limit + 1;
    }
}
