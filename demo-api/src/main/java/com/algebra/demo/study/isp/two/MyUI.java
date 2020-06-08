package com.algebra.demo.study.isp.two;

/**
 * @author al
 * @date 2020/6/8 15:40
 * @description 页面接口
 */
public interface MyUI {

    void requestDepositAmount();

    void requestWithdrawalAmount();

    void requestTransferAmount();

    void informInsufficientFunds();

}
