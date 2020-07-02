package com.algebra.authentication.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/7/2 9:02
 * @description
 */
@Data
public class WebApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success = true;

    private T data;

    private String message;

    private String errorMessage;

    private int count;

    private int code;

    public WebApiResult() {}

    public WebApiResult(int code, boolean success, String message, int count, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
        this.count = count;
        this.code = 200;
    }

    public static <T> WebApiResult<T> ok(T data) {
        return new WebApiResult<>(200, true, "success", 1, data);
    }

    public static <T> WebApiResult<T> ok() {
        return new WebApiResult<>(200, true, "success", 0, null);
    }

    public static <T> WebApiResult<T> ok(String message) {
        return new WebApiResult<>(200, true, message, 0, null);
    }

    public static <T> WebApiResult<T> error(Exception ex) {
        String exceptionMessageFormat = "Message: %s, StackTrace: %s, Suppressed: %s, Cause: %s, Class: %s %s";

        String msg = String.format(exceptionMessageFormat, ex.getMessage(), ex.getStackTrace(), ex.getSuppressed(),
                ex.getCause(), ex.getClass(), System.getProperty("line.separator"));
        return error(msg);
    }

    public static <T> WebApiResult<T> error(String message) {
        return new WebApiResult<T>(500, false, message, 0, null);
    }

    public static <T> WebApiResult<T> error(int code, String message) {
        return new WebApiResult<T>(code, false, message, 0, null);
    }

}
