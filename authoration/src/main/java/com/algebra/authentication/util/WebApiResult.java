package com.algebra.authentication.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2020/7/2 9:02
 * @description
 */
@Data
@ApiModel("API通用相应信息实体")
public class WebApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "接口相应状态：true - 成功， false - 失败")
    private Boolean success = true;

    @ApiModelProperty(value = "返回的数据实体")
    private T data;

    @ApiModelProperty(value = "接口提示信息")
    private String message;

    @ApiModelProperty(value = "接口提示异常信息")
    private String errorMessage;

    @ApiModelProperty(value = "列表分页查询的总条数")
    private int count;

    @ApiModelProperty(value = "特殊相应编码，ex: 401-授权失败")
    private int code;

    public WebApiResult() {}

    public WebApiResult(int code, boolean success, String message, int count, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    public static <T> WebApiResult<T> ok(T data) {
        return new WebApiResult<>(200, true, "success", 1, data);
    }

    public static <T> WebApiResult<T> ok(T data, String message) {
        return new WebApiResult<>(200, true, message, 1, data);
    }

    public static <T> WebApiResult<T> ok(T data, int count) {
        return new WebApiResult<>(200, true, "查询成功！", count, data);
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
