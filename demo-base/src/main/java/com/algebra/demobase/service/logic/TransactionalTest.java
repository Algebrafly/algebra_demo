package com.algebra.demobase.service.logic;

import com.algebra.demobase.entity.AccountMoney;
import com.algebra.demobase.entity.AccountMsg;
import com.algebra.demobase.service.AccountMoneyService;
import com.algebra.demobase.service.AccountMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author al
 * @date 2020/5/18 11:20
 * @description
 */
@Service
@Slf4j
public class TransactionalTest {

    @Autowired
    AccountMoneyService moneyService;

    @Autowired
    AccountMsgService msgService;

    @Transactional(rollbackFor = Exception.class)
    public void updateAccountTest(AccountMoney accountMoney) {

        AccountMsg msg = new AccountMsg();
        msg.setBizPk(accountMoney.getBizPk());
        msg.setChangeDate(new Date());
        msg.setChangeRemark("修改用户："+accountMoney.getAcctName()+"，金额："+accountMoney.getChangeAmt()+"元");
        msgService.insertSelective(msg);
        moneyService.updateByPrimaryKeySelective(accountMoney);

    }



}
