package com.algebra.demo.util.aspect.common;

import com.algebra.demo.annotation.TraceEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author al
 * @date 2020/2/10 16:10
 * @description
 */
@Component
@Slf4j
@Aspect
public class WebTraceAspect {


    ThreadLocal<Long> startTime=new ThreadLocal<>();

    /**
     * 切入点定义，凡是加了TraceEvent注解的web方法都会被施加MDC
     */
    @Pointcut("@annotation(traceEvent)")
    public void webTraceLog(TraceEvent traceEvent){}

    @Before(value = "webTraceLog(traceEvent)")
    public void doBefore(ProceedingJoinPoint joinPoint, TraceEvent traceEvent){
        //获取请求报文头部元数据
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求对象
        HttpServletRequest request = requestAttributes.getRequest();

        Long millis = System.currentTimeMillis();

        MDC.put("eventId",String.valueOf(millis));

        //记录控制器执行前的时间毫秒数

        startTime.set(millis);

        log.info("前置通知执行：");

        log.info("url:"+request.getRequestURL());
        log.info("method:"+request.getMethod());
        log.info("ip:"+request.getRemoteAddr());
        log.info("class_method:"+joinPoint.getSignature().getDeclaringTypeName()+ "."+joinPoint.getSignature().getName());
        log.info("args:"+ Arrays.toString(joinPoint.getArgs()));

        log.info("流水号:"+millis);
    }

    @After("webTraceLog(traceEvent)")
    public void doFinally(ProceedingJoinPoint joinPoint, TraceEvent traceEvent) {

    }


}
