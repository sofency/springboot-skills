package com.example.templateutils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>Project: springboot-skills - ThreadPoolConfig
 * <p>Powered by echo On 2024-08-23 14:27:32
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "customThreadPool")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // CPU 密集型核心线程数设置为CPU核数+1 / IO密集型CPU核数2倍多
        executor.setCorePoolSize(5); // 核心线程数
        executor.setMaxPoolSize(10); // 最大线程数
        executor.setQueueCapacity(20); // 队列容量
        executor.setKeepAliveSeconds(30); // 线程存活时间
        executor.setThreadNamePrefix("custom-thread-"); // 线程名前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略
        return executor;
    }
}