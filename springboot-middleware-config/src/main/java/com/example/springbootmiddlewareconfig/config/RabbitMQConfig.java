package com.example.springbootmiddlewareconfig.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * <p>Project: springboot-skills - RabbitMqConfig
 * <p>Powered by echo On 2024-08-23 11:06:58
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 */
@Configuration
public class RabbitMQConfig {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDefaultRequeueRejected(false); // 消息被拒绝后是否重新入队列
        factory.setAdviceChain(retryInterceptor()); // 设置重试策略
        return factory;
    }

    /**
     * 设置重试策略
     *
     * @return 重试拦截器
     */
    @Bean
    public RetryOperationsInterceptor retryInterceptor() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(5) // 最大重试次数
                .backOffOptions(1000, 2.0, 10000) // 初始间隔1秒，乘数2，最大间隔10秒
                .build();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}