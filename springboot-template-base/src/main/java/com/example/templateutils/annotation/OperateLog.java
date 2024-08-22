package com.example.templateutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Project: springboot-skills - OperateLog
 * <p>Powered by echo On 2024-08-22 08:45:03
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperateLog {
    String type(); // 操作类型
}
