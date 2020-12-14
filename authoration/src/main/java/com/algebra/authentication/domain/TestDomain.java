package com.algebra.authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author al
 * @date 2020/12/14 9:09
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDomain extends BaseDomain implements Serializable {

    private String name;

    private Date createTime;

}
