package com.example.springbootmiddlewareconfig.producer;

import com.example.springbootmiddlewareconfig.handler.KafkaSendResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>Project: springboot-skills - KafkaMessageSender
 * <p>Powered by echo On 2024-08-22 20:43:07
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Component
// @Transactional(rollbackFor = RuntimeException.class) 开启事务后才生效
public class KafkaMessageProducer {
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplate.setProducerListener(new KafkaSendResultHandler());
    }

    /**
     * 发送消息
     * @param topic 主题
     * @param data 要发送的数据
     */
    public void sendMessage(String topic, String data) {
        kafkaTemplate.send(topic, data);
    }
}
