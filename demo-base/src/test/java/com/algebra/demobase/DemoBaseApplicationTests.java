package com.algebra.demobase;

import com.algebra.demobase.entity.domain.Person;
import com.algebra.demobase.mapper.PersonMapper;
import com.algebra.demobase.service.PersonService;
import com.algebra.demo.util.base.Builder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoBaseApplicationTests {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    PersonService personService;

    @Test
    public void contextLoads() {
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
