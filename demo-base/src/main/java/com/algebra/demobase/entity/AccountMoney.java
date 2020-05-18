package com.algebra.demobase.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
  * @author al
  * @date 2020/5/18 11:18
  * @description 
  */
@Data
public class AccountMoney implements Serializable {
    /**
    * 业务主键
    */
    private String bizPk;

    /**
    * 账户
    */
    private String acctNo;

    /**
    * 账户姓名
    */
    private String acctName;

    /**
    * 账户余额
    */
    private BigDecimal acctBalance;

    private BigDecimal changeAmt;

    /**
    * 交易备注
    */
    private String transRemark;

    private static final long serialVersionUID = 1L;
}