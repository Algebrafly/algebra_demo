package com.algebra.demobase;

import com.algebra.demobase.entity.AccountMoney;
import com.algebra.demobase.entity.domain.Person;
import com.algebra.demobase.mapper.PersonMapper;
import com.algebra.demobase.service.AccountMoneyService;
import com.algebra.demobase.service.PersonService;
import com.algebra.demo.util.base.Builder;
import com.algebra.demobase.service.logic.TransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoBaseApplicationTests {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    PersonService personService;

    @Autowired
    AccountMoneyService moneyService;

    @Autowired
    TransactionalTest transactionalTest;

    @Test
    public void contextLoads() {
    }

    @Test
    public void transTest(){
        AccountMoney money = new AccountMoney();
        money.setBizPk("my_1001");
        money.setAcctBalance(new BigDecimal(3000));
        money.setChangeAmt(new BigDecimal(1001));
        money.setAcctName("张三");
        money.setTransRemark("取款1");

        transactionalTest.updateAccountTest(money);
    }

    @Test
    public void moneyServiceTest(){
        AccountMoney money = new AccountMoney();
        money.setBizPk("my_1001");
        money.setAcctBalance(new BigDecimal("5000.00"));
        money.setAcctName("张三");
        money.setAcctNo("622848026395546123");
        money.setTransRemark("一次性存款");
        int i = moneyService.insertSelective(money);
        Assert.assertEquals(i,1);
    }

    @Test
    public void mapperTest(){
        Person person = Builder.of(Person::new)
                .with(Person::setPersonId, "p-001")
                .with(Person::setPersonName,"tom")
                .with(Person::setPersonAge,20)
                .with(Person::setPersonBirthday, new Date())
                .with(Person::setRemark,"hello world").build();
        int i = personMapper.insert(person);
        Assert.assertEquals(i,1);
    }

    @Test
    public void serviceTest(){
        Person person = Builder.of(Person::new)
                .with(Person::setPersonId, "person-001")
                .with(Person::setPersonName,"tom")
                .with(Person::setPersonAge,20)
                .with(Person::setPersonBirthday, new Date())
                .with(Person::setRemark,"hello world").build();
        int i = personService.insert(person);
        System.out.println(i);
    }

    @Test
    public void utilTest(){

    }

}
