package com.algebra.demo.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author al
 * @date 2020/3/2 15:30
 * @description
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(CheckTimeInterval.List.class)
@Constraint(validatedBy = CheckTimeIntervalValidator.class)
public @interface CheckTimeInterval {

    String startTime() default "from";

    String endTime() default "to";

    String message() default "{org.hibernate.validator.referenceguide.chapter06.CheckCase.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        CheckTimeInterval[] value();
    }

}
