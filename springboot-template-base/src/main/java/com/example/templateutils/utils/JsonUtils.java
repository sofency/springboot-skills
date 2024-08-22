package com.example.templateutils.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
public class JsonUtils {
    // 创建一个ObjectMapper实例，并配置它以美观的格式打印JSON（可选）
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * 将Java对象转换为JSON字符串
     * @param obj
     * @return
     * @throws Exception
     */
    public static String toJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 将JSON字符串转换为Java对象
     * @param json
     * @param clazz
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * json to list
     * @param json
     * @param clazz
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    // 禁止实例化工具类
    private JsonUtils() {
        throw new AssertionError("Utility class");
    }
}
