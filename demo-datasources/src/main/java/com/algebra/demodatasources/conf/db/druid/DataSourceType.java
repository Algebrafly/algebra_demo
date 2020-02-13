package com.algebra.demodatasources.conf.db.druid;

import java.lang.annotation.*;

/**
 * @author al
 * @date 2020/2/12 16:12
 * @description 自定义数据源类型注解
 */
@Documented
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceType {

    String value() default DataSourceConstants.MASTER;
}
