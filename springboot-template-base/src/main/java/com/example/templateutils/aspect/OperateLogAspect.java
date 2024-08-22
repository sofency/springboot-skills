package com.example.templateutils.aspect;

import com.example.templateutils.annotation.OperateLog;
import com.example.templateutils.entity.OperationLog;
import com.example.templateutils.utils.IpUtils;
import com.example.templateutils.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

// 替换了javax
import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p>Project: springboot-skills - OperateLogAspect
 * <p>Powered by echo On 2024-08-22 08:52:16
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 * 当用户操作完成后 记录下该操作记录
 */
@Aspect
@Component
@Slf4j
public class OperateLogAspect {
    @Pointcut("@annotation(com.example.templateutils.annotation.OperateLog)")
    public void operateLogPointCut(){}

    /**
     *
     * @param joinPoint 切入点
     * @param keys 定义返回的结果命名字段
     */
    @AfterReturning(value = "operateLogPointCut()", returning = "keys")
    public void saveOperateLog(JoinPoint joinPoint, Object keys) throws Exception {
        // 当前请求的所有请求信息都存储在RequestAttributes中
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 获取servletRequest信息
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = className + "." + method.getName();
        OperateLog operateLog = method.getAnnotation(OperateLog.class);
        String ipAddress = IpUtils.getIpAddress(request);
        OperationLog operationLog = OperationLog.builder().optDesc(operateLog.type())
                .optMethod(methodName)
                .optUrl(Objects.requireNonNull(request).getRequestURI())
                .requestParam(JsonUtils.toJson(joinPoint.getArgs()))
                .responseData(JsonUtils.toJson(keys))
                .requestMethod(Objects.requireNonNull(request).getMethod())
                .ipAddress(ipAddress)
                .ipSource(IpUtils.getIpSource(ipAddress))
                .build();

        log.info("current log behavior is {}", JsonUtils.toJson(operationLog));
    }
}
