package com.algebra.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/6/23 11:57
 * @description
 */
@Data
public class DemoUser implements Serializable {

    private Integer userId;
    private String userName;

}
