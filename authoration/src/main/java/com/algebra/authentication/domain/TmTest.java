package com.algebra.authentication.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
  * @author al
  * @date 2020/7/7 11:01
  * @description 
  */
@Data
@TableName(value = "tm_aa")
public class TmTest implements Serializable {

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @TableField(value = "cid")
    private Integer cid;

    @TableField(value = "username")
    private String username;

    @TableField(value = "usertel")
    private String userTel;

    @TableField(value = "useridtype")
    private String userIdType;

    @TableField(value = "risklevel")
    private Integer riskLevel;

    @TableField(value = "contractno")
    private String contractNo;

    @TableField(value = "vinnumber")
    private String vinNumber;

    @TableField(value = "platenumber")
    private String plateNumber;

    @TableField(value = "carmold")
    private Integer carMold;

    @TableField(value = "loandate")
    private Date loanDate;

    @TableField(value = "inputtime")
    private Date inputTime;

}