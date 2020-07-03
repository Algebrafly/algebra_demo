package com.algebra.authentication.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wf.jwtp.exception.TokenException;

/**
 * @author al
 * @date 2020/7/3 11:16
 * @description 指定测试类异常处理（增强Controller）
 */
@ControllerAdvice(basePackageClasses = {com.algebra.authentication.web.TestController.class})
public class TokenExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public WebApiResult<String> errorHandler(Exception ex) {
        if (ex instanceof TokenException) {
            return WebApiResult.error(((TokenException) ex).getCode(),ex.getMessage());
        } else {
            return WebApiResult.error(ex.getMessage());
        }
    }
}
