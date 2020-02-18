package com.algebra.demoshard.entity.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * @author al
  * @date 2020/2/17 16:54
  * @description 
  */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TUser implements Serializable {
    private Long id;

    /**
    * 名称
    */
    private String name;

    /**
    * 城市
    */
    private Integer cityId;

    /**
    * 性别
    */
    private Boolean sex;

    /**
    * 电话
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 密码
    */
    private String password;

    private static final long serialVersionUID = 1L;
}