package com.algebra.demo.study.isp.two;

/**
 * @author al
 * @date 2020/6/8 15:43
 * @description 退出界面
 */
public interface WithdrawalUI extends MyUI{

    @Override
    void requestWithdrawalAmount();

    @Override
    void informInsufficientFunds();

}
