package com.example.springbootmiddlewareconfig.producer;

import com.example.springbootmiddlewareconfig.config.DelayExchangeConfig;
import com.example.springbootmiddlewareconfig.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Project: springboot-skills - RabbitMQMessageProcucer
 * <p>Powered by echo On 2024-08-23 12:04:14
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Component
public class RabbitMQMessageProducer {
    private final AmqpTemplate amqpTemplate;

    public RabbitMQMessageProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendDelayMessage(String message, long delayTimes) {
        amqpTemplate.convertAndSend(DelayExchangeConfig.DELAY_EXCHANGE, DelayExchangeConfig.DELAY_QUEUE,
                message, msg -> {
                    msg.getMessageProperties().setHeader("x-delay", delayTimes);
                    return msg;
                });

    }
}
