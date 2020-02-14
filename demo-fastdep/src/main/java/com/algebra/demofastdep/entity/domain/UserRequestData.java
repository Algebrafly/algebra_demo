package com.algebra.demofastdep.entity.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
  * @author al
  * @date 2020/2/14 11:41
  * @description 
  */
@Data
public class UserRequestData implements Serializable {
    /**
    * 主键
    */
    private Long userId;

    private String nickname;

    private String avatar;

    private Date createdTime;

    private Date updateTime;

    private String query;

    private String headers;

    private static final long serialVersionUID = 1L;
}