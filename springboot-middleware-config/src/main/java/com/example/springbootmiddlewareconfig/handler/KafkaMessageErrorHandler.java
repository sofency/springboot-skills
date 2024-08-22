package com.example.springbootmiddlewareconfig.handler;

import com.example.templateutils.utils.JsonUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * <p>Project: springboot-skills - KafkaMessageErrorHandler
 * <p>Powered by echo On 2024-08-22 20:50:40
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Slf4j
@Component
public class KafkaMessageErrorHandler implements KafkaListenerErrorHandler {
    @Override
    @NonNull
    public Object handleError(@NonNull Message<?> message, @NonNull ListenerExecutionFailedException exception) {
        return new Object();
    }

    @Override
    @NonNull
    public Object handleError(@NonNull Message<?> message, @NonNull ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        log.info("message detail: {}, exception: {}, consumer: {}, topic:{}",
                message, exception, consumer.groupMetadata().toString(), JsonUtils.toJson(consumer.listTopics()));
        return KafkaListenerErrorHandler.super.handleError(message, exception, consumer);
    }
}
