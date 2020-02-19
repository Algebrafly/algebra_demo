package com.algebra.demoshard.entity.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_user")
public class TUser implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
    * 名称
    */
    private String name;

    /**
    * 城市
    */
    private int cityId;

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