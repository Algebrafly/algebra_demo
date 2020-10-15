package com.algebra.authentication.util.userlog;

import java.lang.annotation.*;

/**
 * @author al
 * @date 2020/10/15 11:51
 * @description
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAsp {
    String value() default "";
}
