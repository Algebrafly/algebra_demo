package com.algebra.demo.annotation;

import java.lang.annotation.*;

/**
 * @author al
 * @date 2020/2/10 16:07
 * @description
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TraceEvent {


}
