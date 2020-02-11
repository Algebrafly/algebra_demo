package com.algebra.demo.annotation;

import java.lang.annotation.*;

/**
 * @author al
 * 免登录校验注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Annoymous {
}