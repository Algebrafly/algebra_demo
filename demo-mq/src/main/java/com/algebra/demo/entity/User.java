package com.algebra.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/2/28 17:37
 * @description
 */
@Data
public class User implements Serializable {

    private String name;

    private String password;

}
