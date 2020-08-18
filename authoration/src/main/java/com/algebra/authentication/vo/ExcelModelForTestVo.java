package com.algebra.authentication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author al
 * @date 2020/8/18 16:40
 * @description
 */
@Data
public class ExcelModelForTestVo implements Serializable {

    /**
     * 车辆SN
     */
    private String vehicleSn;

    /**
     * 车架号
     */
    private String vinNumber;

    /**
     * 设备号
     */
    private String deviceNumber;

    /**
     * 蓝牙密钥
     */
    private String bluetoothKey;

    /**
     * 生产日期
     */
    private Date productionDate;

    /**
     * 车型（文字值）
     */
    private String vehicleModel;

    /**
     * 设备型号（文字值）
     */
    private String deviceModelDesc;

}
