package com.algebra.demo.annotation;

import java.lang.annotation.*;

/**
 * @author al
 * @date 2020/2/4 11:45
 * @description
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAspectAnnotation {
    /**
     * 使用场景
     * @return
     */
    String sense();
}
