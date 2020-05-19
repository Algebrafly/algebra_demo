package com.algebra.demobase.service.logic;

import com.algebra.demobase.entity.AccountMoney;
import com.algebra.demobase.entity.AccountMsg;
import com.algebra.demobase.service.AccountMoneyService;
import com.algebra.demobase.service.AccountMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional(rollbackFor = Exception.class)
    public void updateAccountTest2(AccountMoney accountMoney) {

        try {
            AccountMsg msg = new AccountMsg();
            msg.setBizPk(accountMoney.getBizPk());
            msg.setChangeDate(new Date());
            msg.setChangeRemark("修改用户："+accountMoney.getAcctName()+"，金额："+accountMoney.getChangeAmt()+"元");
            try {
                msgService.insertSelective(msg);
            } catch (Exception e) {
                log.error("insert service exception, msg : {}", e.getMessage());
                moneyService.updateByPrimaryKeySelective(accountMoney);
//                throw e;
            }
        } catch (Exception e) {
            log.error("Transactional exception， msg :{}", e.getMessage());
            throw e;
        }

    }





}
