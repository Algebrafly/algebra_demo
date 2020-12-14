package com.algebra.authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/12/14 9:28
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomain implements Serializable {

    private String timeZone;

    private String language;

}
