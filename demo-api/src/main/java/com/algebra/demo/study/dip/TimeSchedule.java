package com.algebra.demo.study.dip;

/**
 * @author al
 * @date 2020/6/5 14:34
 * @description 定时开关控制：一种行为接口
 */
public interface TimeSchedule {


    /**
     * 判断是否超时
     * @param timing 定时时间
     * @return boolean
     */
    boolean isTimeOut(Long timing);


}
