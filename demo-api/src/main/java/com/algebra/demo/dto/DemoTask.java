package com.algebra.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author al
 * @date 2020/6/23 11:57
 * @description
 */
@Data
public class DemoTask implements Serializable {

    private Integer taskId;

    private BigDecimal amount;

}
