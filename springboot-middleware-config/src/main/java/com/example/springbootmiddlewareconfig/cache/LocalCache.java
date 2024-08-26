package com.example.springbootmiddlewareconfig.cache;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <p>Project: springboot-skills - LocalCache
 * <p>Powered by echo On 2024-08-26 20:55:18
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
public interface LocalCache {
    List<String> get(String key);

    void invalidate(String key);
}
