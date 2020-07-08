package com.algebra.authentication.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author al
 * @date 2020/7/8 15:15
 * @description
 */
@Data
public class PageRequestParam implements Serializable {

    private Integer currentPage = 0;

    private Integer pageSize = 10;

    private Map<String,Object> extraParam;

}
