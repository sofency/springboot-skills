package com.example.templateutils.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>Project: springboot-skills - JsonUtils
 * <p>Powered by echo On 2024-08-21 22:37:55
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 * 主要使用Jackson进行json的转换 对象<->json  数组<-> json数组
 */
@Slf4j
public class JsonUtils {
    // 创建一个ObjectMapper实例，并配置它以美观的格式打印JSON（可选）
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * 将Java对象转换为JSON字符串
     *
     * @param obj 参数
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("object parse to json error {}", obj);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将JSON字符串转换为Java对象
     *
     * @param json
     * @param clazz
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("json parse to object error {}", json);
            throw new RuntimeException(e);
        }
    }

    /**
     * json to list
     *
     * @param json
     * @param clazz
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz){
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            log.error("json parse to list error {}", json);
            throw new RuntimeException(e);
        }
    }

    // 禁止实例化工具类
    private JsonUtils() {
        throw new AssertionError("Utility class");
    }
}
