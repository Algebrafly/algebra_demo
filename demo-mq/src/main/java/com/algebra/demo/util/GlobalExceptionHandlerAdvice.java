package com.algebra.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author al
 * @date 2020/3/2 16:00
 * @description 接口参数校验失败的，可以在这里进行统一处理，并返回。
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler({ConstraintViolationException.class})
    public String handler(HttpServletRequest request, ConstraintViolationException ex){
        StringBuffer sb = new StringBuffer();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            sb.append(violation.getMessage());
        }
        return sb.toString();
    }

}
