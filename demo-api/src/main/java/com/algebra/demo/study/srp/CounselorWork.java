package com.algebra.demo.study.srp;

/**
 * @author al
 * @date 2020/6/28 9:12
 * @description
 */
public class CounselorWork implements ICounselorWork {
    @Override
    public void classManager() {
        System.out.println("班级管理");
    }

    @Override
    public void feeCollection() {
        System.out.println("费用收取");
    }

    @Override
    public void psychologicalCounseling() {
        System.out.println("心理辅导");
    }

    @Override
    public void attendanceStatistics() {
        System.out.println("出勤统计");
    }
}
