package com.algebra.authentication.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2021/7/19 14:27
 * @description
 */
@Aspect
@Slf4j
@Component
public class CallApiCountAsp {

    @Pointcut("@annotation(apiCall)")
    public void callApiCount(ApiCall apiCall){}


    @Around(value = "callApiCount(apiCall)", argNames = "joinPoint,apiCall")
    public Object doAround(ProceedingJoinPoint joinPoint, ApiCall apiCall){

        log.info("记录接口调用次数");


        Object ret = null;
        try {
             ret = joinPoint.proceed();
        } catch (Throwable throwable) {

        }
        return ret;
    }


}
