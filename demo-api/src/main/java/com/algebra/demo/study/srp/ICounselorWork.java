package com.algebra.demo.study.srp;

/**
 * @author al
 * @date 2020/6/28 9:06
 * @description
 */
public interface ICounselorWork {
    /**
     * 班级管理
     */
    void classManager();

    /**
     * 费用催缴
     */
    void feeCollection();

    /**
     * 心理辅导
     */
    void psychologicalCounseling();

    /**
     * 出勤统计
     */
    void attendanceStatistics();
}
