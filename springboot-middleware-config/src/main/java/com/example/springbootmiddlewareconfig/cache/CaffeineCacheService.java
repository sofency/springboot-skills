package com.example.springbootmiddlewareconfig.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Project: springboot-skills - CaffeineCacheService
 * <p>Powered by echo On 2024-08-26 21:19:34
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 * 最近最少频率使用 LFU  一个近乎最佳的命中率
 *
 */
public class CaffeineCacheService implements LocalCache {
    private static final LoadingCache<String, List<String>> loadingCache = Caffeine.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES)
            .expireAfterAccess(3, TimeUnit.MINUTES)
            .build(new CacheLoader<>() {
                @Override
                public @Nullable List<String> load(@NonNull String s) throws Exception {
                    return null;
                }
            });

    @Override
    public List<String> get(String key) {
        return loadingCache.get(key);
    }

    @Override
    public void invalidate(String key) {
        loadingCache.invalidate(key);
    }
}
