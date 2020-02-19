package com.algebra.demoshard.web;

import com.algebra.demoshard.entity.domain.TableOne;
import com.algebra.demoshard.service.ShardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ShardController {

    @Resource
    private ShardService shardService ;

    @RequestMapping("/createTable")
    public String createTable (){
        shardService.createTable();
        return "success" ;
    }

    @RequestMapping("/insertOne")
    public String insertOne (){
        shardService.insertOne();
        return "SUCCESS" ;
    }

    @RequestMapping("/selectOneByPhone/{phone}")
    public TableOne selectOneByPhone (@PathVariable("phone") String phone){
        log.info("服务被调用... ");
        return shardService.selectOneByPhone(phone);
    }
}
