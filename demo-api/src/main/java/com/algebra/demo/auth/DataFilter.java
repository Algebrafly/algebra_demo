package com.algebra.demo.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * 使用方式：详见Readme.md
 *
 * @apiNote 权限过滤器
 * @since 2021/2/22 13:42
 * @author al
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface DataFilter {

     /**
      * 机构别名
      * @return
      */
     public String custstoreidAlias() default "custstoreid";

     /**
      * 设备号别名
      * @return
      */
     public String equipmentAlias() default "deviceid";

}
