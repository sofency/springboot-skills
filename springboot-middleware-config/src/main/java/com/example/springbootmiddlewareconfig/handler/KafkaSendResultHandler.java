package com.example.springbootmiddlewareconfig.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * <p>Project: springboot-skills - KafkaSendResultHandler
 * <p>Powered by echo On 2024-08-22 21:03:47
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Slf4j
public class KafkaSendResultHandler implements ProducerListener<Object, Object> {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("消息发送成功：{}", producerRecord.toString());
    }

    @Override
    public void onError(ProducerRecord producerRecord, @Nullable RecordMetadata recordMetadata, Exception exception) {
        log.info("消息发送失败：{}", producerRecord.toString() + exception.getMessage());
    }

}
