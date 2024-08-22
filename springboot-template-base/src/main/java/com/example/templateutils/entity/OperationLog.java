package com.example.templateutils.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>Project: springboot-skills - OperationLog
 * <p>Powered by echo On 2024-08-22 09:40:16
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationLog {
    /**
     * 操作路径
     */
    private String optUrl;

    /**
     * 操作方法
     */
    private String optMethod;

    /**
     * 操作描述
     */
    private String optDesc;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 返回数据
     */
    private String responseData;

    /**
     * 用户登录ip
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;
}
