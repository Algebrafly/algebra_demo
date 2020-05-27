package com.algebra.demo.study.coursetemp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author al
 * @date 2020/5/27 16:23
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple extends Fruit {

    private String address;

    private Date outDate;
}
