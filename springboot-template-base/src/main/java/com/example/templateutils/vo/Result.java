package com.example.templateutils.vo;

import com.example.templateutils.constant.Constant;
import com.example.templateutils.enums.StatusInfoEnum;
import lombok.Data;

/**
 * <p>Project: springboot-skills - Result
 * <p>Powered by echo On 2024-08-22 10:27:14
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Data
public class Result<T> {
    /**
     * 返回状态
     */
    private Boolean flag;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> ok() {
        return restResult(true, null, Constant.SUCCESS_CODE, StatusInfoEnum.SUCCESS.getDesc());
    }

    public static <T> Result<T> ok(T data) {
        return restResult(true, data, Constant.SUCCESS_CODE, StatusInfoEnum.SUCCESS.getDesc());
    }

    public static <T> Result<T> ok(T data, String message) {
        return restResult(true, data, Constant.SUCCESS_CODE, message);
    }

    public static <T> Result<T> fail() {
        return restResult(false, null, Constant.ERROR_CODE, StatusInfoEnum.FAIL.getDesc());
    }

    public static <T> Result<T> fail(StatusInfoEnum StatusCode) {
        return restResult(false, null,  Constant.ERROR_CODE, StatusCode.getDesc());
    }

    public static <T> Result<T> fail(String message) {
        return restResult(false, message);
    }

    public static <T> Result<T> fail(T data) {
        return restResult(false, data,  Constant.ERROR_CODE, StatusInfoEnum.FAIL.getDesc());
    }

    public static <T> Result<T> fail(T data, String message) {
        return restResult(false, data,  Constant.ERROR_CODE, message);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return restResult(false, null, code, message);
    }

    private static <T> Result<T> restResult(Boolean flag, String message) {
        Result<T> apiResult = new Result<>();
        apiResult.setFlag(flag);
        apiResult.setCode(flag ?  Constant.SUCCESS_CODE :  Constant.ERROR_CODE);
        apiResult.setMessage(message);
        return apiResult;
    }

    private static <T> Result<T> restResult(Boolean flag, T data, Integer code, String message) {
        Result<T> apiResult = new Result<>();
        apiResult.setFlag(flag);
        apiResult.setData(data);
        apiResult.setCode(code);
        apiResult.setMessage(message);
        return apiResult;
    }
}
