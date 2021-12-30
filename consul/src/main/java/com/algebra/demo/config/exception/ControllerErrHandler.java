package com.algebra.demo.config.exception;

import com.algebra.demo.config.WebApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @Author: Duxuewei
 * @Description: 公共异常处理类
 * @Version: v1.0.0
 * @Date Create in 2021/1/19 12:08
 */
@ControllerAdvice
@Slf4j
public class ControllerErrHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebApiResult exceptionHandling(Exception ex) {
        String exceptionMessageFormat = "Message: %s: %s \n Suppressed: %s";
        String msg = String.format(exceptionMessageFormat,
                ex.getClass().getName(), ex.getMessage(), StringUtils.join(ex.getStackTrace()), "\n");
        log.error(msg);
        return WebApiResult.error(ex.getMessage());
    }

    public static String parseExceptionMsg(Exception ex, String errMsg) {
        if (Objects.isNull(ex)) {
            return "";
        }
        String exceptionMessageFormat = "Message: %s: %s \n Suppressed: %s";

        return String.format(exceptionMessageFormat,
                ex.getClass().getName(), ex.getMessage(), StringUtils.join(ex.getStackTrace()), "\n");
    }

}
