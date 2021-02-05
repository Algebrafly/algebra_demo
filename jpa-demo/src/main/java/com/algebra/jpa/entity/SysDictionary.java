package com.algebra.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author al
 * @date 2021/2/5 11:27
 * @description
 */
@Data
@Entity
@Table(name = "sys_dictionary", schema = "test")
public class SysDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private String code;
    private String content;
    private String contentEn;
    private String contentJp;
    private String pattern;
    private String remark;
    private String appKey;
    private Byte deleted;
}
