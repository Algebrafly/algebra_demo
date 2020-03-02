package com.algebra.demo.entity;

import com.algebra.demo.util.CheckTimeInterval;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author al
 * @date 2020/3/2 15:22
 * @description
 */
@Data
@CheckTimeInterval(startTime = "releaseStartTime",endTime = "releaseEndTime",message = "发放开始时间不能大于结束时间")
public class CouponForm {

    @NotNull(groups = {}, message = "优惠券ID不能为空")
    private Long couponId;

    @NotNull(groups = {}, message = "商户ID不能为空")
    private Integer merchantId;

    @NotNull(groups = {}, message = "优惠券名称不能为空")
    @Length(groups = {}, max = 16, message = "优惠券名称最长16位")
    private String couponName;

    @NotNull(groups = {}, message = "优惠券类型不能为空")
    private String couponType;

    @NotNull
    private Integer parValue;

    private Integer quantity;

    private Date releaseStartTime;

    private Date releaseEndTime;

    private Integer validityMode;


    @Size(max = 200)
    private String remark;


}
