package com.example.springbootmiddlewareconfig.cache;

import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p>Project: springboot-skills - GuavaCacheService
 * <p>Powered by echo On 2024-08-26 20:57:37
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
public class GuavaCacheService<T> implements LocalCache {
    private static final LoadingCache<String, List<String>> loadingCache;

    // guava 只存储非null 数据
    static {
        loadingCache = CacheBuilder.newBuilder()
                .concurrencyLevel(Runtime.getRuntime().availableProcessors()) // 设置并发数量
                // 配置最大容量为100，基于容量进行回收
                .initialCapacity(1000)

                //设置过期时间(3秒内没有使用)在指定时间内没有进行读写，会移除key，下次取的时候从loading中取
                .expireAfterAccess(3, TimeUnit.MINUTES)
                // 配置写入后多久使缓存过期
                .expireAfterWrite(3, TimeUnit.MINUTES)
                // 配置写入后多久刷新缓存
                // .refreshAfterWrite(3, TimeUnit.MINUTES)
                // value使用弱引用-WeakReference
                .weakValues()
                // 当Entry被移除时的监听器-下文会讲述
                .removalListener((RemovalListener<String, List<String>>) notification -> System.out.println(notification.getKey() + "-" + notification.getValue() + " is remove"))
                // 创建一个CacheLoader，重写load方法，以实现"当get时缓存不存在，则load，放到缓存并返回的效果
                .build(new CacheLoader<>() {
                    // 重点，自动写缓存数据的方法，必须要实现
                    @Override
                    public List<String> load(String key) throws Exception {
                        return new ArrayList<>();
                    }
                });
    }

    @Override
    public List<String> get(String key) {
        // return loadingCache.getIfPresent(key); 缓存不存在 直接返回null 静静等待缓存过期
        return loadingCache.getUnchecked(key); // 缓存不存在 load一下
        // return loadingCache.get(key); // 会跑异常
    }

    // 这样可以通过消费者通知清除key
    @Override
    public void invalidate(String key) {
        loadingCache.invalidate(key);
    }
}
