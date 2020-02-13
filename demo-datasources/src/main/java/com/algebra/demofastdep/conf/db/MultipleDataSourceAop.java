package com.algebra.demofastdep.conf.db;

import com.algebra.demofastdep.conf.db.druid.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源自动切换通知类<br>
 * <p>
 * 首先判断当前类是否被该DataSourceType注解进行注释，如果没有指定注解，则采用默认的数据源配置; <br>
 * 如果有，则读取注解中的value值，将数据源切到value指定的数据源
 *
 * @author al
 * @date 2020/2/12
 */

@Aspect
@Component
@Order(0)  // execute before @Transactional
@Slf4j
public class MultipleDataSourceAop {
    /**
     * 拦截 com.**.servicee中所有的方法，根据配置情况进行数据源切换
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* com.algebra.*.service.wrapper.*.*(..))")
//    @Before("@annotation(dataSourceType)")
    public void changeDataSource(JoinPoint joinPoint) throws Throwable {

        try {
            // 拦截的实体类，就是当前正在执行的service
            Class<?> clazz = joinPoint.getTarget().getClass();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 提取目标对象方法注解和类型注解中的数据源标识
            Class<?>[] types = method.getParameterTypes();
            if (clazz.isAnnotationPresent(DataSourceType.class)) {
                DataSourceType source = clazz.getAnnotation(DataSourceType.class);
                MultipleDataSource.setDataSource(source.value());
                log.info("Service Class 数据源切换至--->" + source.value());
            }

            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSourceType.class)) {
                DataSourceType source = m.getAnnotation(DataSourceType.class);
                MultipleDataSource.setDataSource(source.value());
                log.info("Service Method 数据源切换至--->" + source.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法结束后
     */
    @After("execution(* com.algebra.*.service.wrapper.*.*(..))")
//    @After("@annotation(dataSourceType)")
    public void afterReturning() throws Throwable {
        try {
            MultipleDataSource.clearDataSource();
            log.info("数据源已移除！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("数据源移除报错！");
        }

    }
}
