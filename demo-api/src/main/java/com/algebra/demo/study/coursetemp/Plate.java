package com.algebra.demo.study.coursetemp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author al
 * @date 2020/5/27 16:43
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plate<T> {

    private T item;

}
