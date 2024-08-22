package com.example.templateutils.exception;

import com.example.templateutils.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>Project: springboot-skills - BusinessException
 * <p>Powered by echo On 2024-08-22 10:36:31
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private String message;
    private Integer code;

    public BusinessException(String message) {
        this.message = message;
        this.code = Constant.ERROR_CODE;
    }
}
