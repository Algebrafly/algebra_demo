package com.algebra.demobase.service.impl;

import com.algebra.demo.annotation.TestAspectAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author al
 * @date 2020/2/4 18:33
 * @description
 */
@Service
@Slf4j
public class TestAspectService {

    @TestAspectAnnotation(sense = "hello test2")
    public String testService(){
        log.info("hello test!");
        return "hello Test2";
    }

}
