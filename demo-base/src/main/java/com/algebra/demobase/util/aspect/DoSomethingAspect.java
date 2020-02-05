package com.algebra.demobase.util.aspect;

import com.algebra.demo.annotation.DoSomething;
import com.algebra.demo.util.SpelParser;
import com.algebra.demobase.entity.domain.SysUser;
import com.algebra.demobase.entity.domain.User;
import com.algebra.demobase.service.ICacheService;
import com.algebra.demobase.service.ILogService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author al
 * @date 2019/6/10 20:09
 * @description
 */
@Component
@Aspect
@Slf4j
public class DoSomethingAspect {

    @Autowired
    ILogService logService;

    @Autowired
    ICacheService<SysUser> cacheService;

    /**
     * 拦截查询服务：记录调用查询服务日志到数据库，并且将数据进行缓存（可选）
     * @throws Throwable
     */
    @Around("@annotation(doSomething)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, DoSomething doSomething) throws Throwable {
        String key = getKey(doSomething.key(),proceedingJoinPoint);
        String cacheName = doSomething.cacheName();
        // 是否需要记录日志：默认是
        boolean needLog = doSomething.needLog();

        SysUser cacheUser = cacheService.cacheGet(key,cacheName);
        if(cacheUser != null){
            log.info("缓存中查询出来的信息：{}", JSONObject.toJSON(cacheUser));
            return cacheUser;
        }
        Object ret = null;
        try{
            log.info("缓存数据为空，查询MYSQL数据库");
            ret = proceedingJoinPoint.proceed();
        }catch(Exception e){
            log.error("查询出现异常，简单异常信息：{}，详情查看tb_opr_log表",e.getMessage());
            logService.errorLog(this.getClass().getName(),"getUserInfoOne",e.getMessage(),new Date());
        }
        logService.infoLog(this.getClass().getName(),"getUserInfoOne","successful",new Date());
        if(ret != null){
            log.info("查询数据库成功，缓存数据！");
            cacheService.cachePut(key,"user", (SysUser) ret);
        }
        return ret;
    }

    private String getKey(String key, ProceedingJoinPoint proceedingJoinPoint){
        Method method = ((MethodSignature)proceedingJoinPoint.getSignature()).getMethod();
        String[] paramNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        return SpelParser.getKey(key,paramNames,proceedingJoinPoint.getArgs());
    }

}
