package com.algebra.demo.config.exception;

import com.algebra.demo.config.WebApiResult;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author al
 * @date 2022/2/17 10:32
 * @description
 */
@Slf4j
@RestControllerAdvice
public class GlobalParamValidExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebApiResult<Object> validationBodyException(MethodArgumentNotValidException exception) {

        StringBuffer buffer = new StringBuffer();
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                log.error("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() +
                        "},errorMessage{" + fieldError.getDefaultMessage() + "}");
                buffer.append(fieldError.getDefaultMessage()).append(",");
            });
        }
        return WebApiResult.error(buffer.toString());
    }

}
