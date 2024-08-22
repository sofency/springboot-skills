package com.example.templateutils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Project: springboot-skills - StatusInfoEnum
 * <p>Powered by echo On 2024-08-22 10:29:30
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@AllArgsConstructor
@Getter
public enum StatusInfoEnum {
    /**
     * 成功
     */
    SUCCESS("操作成功"),
    /**
     * 没有操作权限
     */
    AUTHORIZED("没有操作权限"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR("系统异常"),
    /**
     * 失败
     */
    FAIL("操作失败");

    /**
     * 描述
     */
    private final String desc;
}
