package com.example.templateutils.handler;

import com.example.templateutils.exception.BusinessException;
import com.example.templateutils.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Project: springboot-skills - ControllerAdviceHandler
 * <p>Powered by echo On 2024-08-22 10:20:33
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdviceHandler {
    /**
     * 针对业务异常进行返回处理
     * @param e 业务异常
     * @return Result
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result<?> errorHandler(BusinessException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }
}
