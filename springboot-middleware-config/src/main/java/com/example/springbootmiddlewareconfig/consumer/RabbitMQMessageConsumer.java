package com.example.springbootmiddlewareconfig.consumer;

import com.example.springbootmiddlewareconfig.config.DelayExchangeConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>Project: springboot-skills - RabbitMQMessageConsumer
 * <p>Powered by echo On 2024-08-23 12:17:36
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 * 手动确认消息
 */
@Slf4j
@Component
public class RabbitMQMessageConsumer {

    @RabbitListener(queues = DelayExchangeConfig.DELAY_QUEUE)
    public void consumeDataRefinementMessages(String info, Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("Consuming Message: {}", info);
            // 手动ack  第二个参数为false是表示仅仅确认当前消息 true表示确认之前所有的消息
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // 手动nack 告诉rabbitmq该消息消费失败  第三个参数：如果被拒绝的消息应该被重新请求，而不是被丢弃或变成死信，则为true
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}