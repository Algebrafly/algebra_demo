package com.algebra.demobase.util.aspect;

import com.algebra.demo.annotation.TestAspectAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author al
 * @date 2020/2/4 11:38
 * @description 测试使用-切面定义
 */
@Component
@Aspect
@Slf4j
public class TestAspect {

    /**
     * 声明一个切入点,指定包下指定类的指定方法（aspectj表达式）
     */
    @Pointcut("execution(public * com.algebra.demobase.web.AspectTestController.test1(..))")
    public void testPointCut(){}
    /**
     * 声明一个切入点,带有注解参数
     */
    @Pointcut(value = "@annotation(testAspectAnnotation)")
    public void testPointCutAnnotation(TestAspectAnnotation testAspectAnnotation){}


    /**
     * 环绕通知-2：灵活自由的在目标方法中切入代码
     * 借助参数传入注解信息
     * @param joinPoint 第一参数
     * @param testAspectAnnotation 自定义参数
     * @return
     */
    @Around(value = "testPointCutAnnotation(testAspectAnnotation)", argNames = "joinPoint,testAspectAnnotation")
//    @Around("@annotation(testAspectAnnotation)")
    public Object doAroundAdvice2(ProceedingJoinPoint joinPoint, TestAspectAnnotation testAspectAnnotation){

        String sense = testAspectAnnotation.sense();
        log.info("切面编程(环绕通知-2)，场景定义：{}",sense);

        // do sth.


        return null;
    }

    /**
     * 环绕通知-1：灵活自由的在目标方法中切入代码
     * 借助反射获取注解信息[不建议使用]
     * @param joinPoint
     * @return
     */
    @Around(value = "testPointCut()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint){

//        TestAspectAnnotation declaredAnnotation = null;
//        try {
//            declaredAnnotation = getDeclaredAnnotation(joinPoint);
//            String sense = declaredAnnotation.sense();
//            log.info("切面编程(环绕通知-1)，场景定义：{}",sense);
//
//            // do sth.
//
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
        log.info("切面编程(环绕通知-1)");

        // do sth.

        return null;
    }

    /**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     */
    public TestAspectAnnotation getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        return objMethod.getDeclaredAnnotation(TestAspectAnnotation.class);
    }

}
