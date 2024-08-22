package com.example.springbootmiddlewareconfig.consumer;

import com.example.templateutils.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * <p>Project: springboot-skills - KafkaMessageConsumer
 * <p>Powered by echo On 2024-08-22 20:43:27
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Slf4j
@Component
public class KafkaMessageConsumer {

    @KafkaListener(topics = {"article"})
    public void consume(ConsumerRecord<?,?> record, Acknowledgment ack) throws Exception {
        log.info("current consume info is {}", JsonUtils.toJson(record.value()));
        // 手动确认
        ack.acknowledge();
    }
}
