package com.example.springbootmiddlewareconfig.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project: springboot-skills - DelayExchangeConfig
 * <p>Powered by echo On 2024-08-23 11:34:34
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 * 延迟队列的绑定
 */
@Configuration
public class DelayExchangeConfig {
    public static final String DELAY_EXCHANGE = "business.delay.exchange";
    public static final String DELAY_QUEUE = "business.delay.queue";

    public static final String DELAY_BINDING_QUEUE = "business.delay.binding.queue";


    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        // 需要安装插件 https://www.jb51.net/program/3224651l5.htm
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue delyQueue() {
        return new Queue(DELAY_QUEUE);
    }

    @Bean
    public Binding delayBindingQueue(CustomExchange delayExchange, Queue delayQueue) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(DELAY_BINDING_QUEUE).noargs();
    }

}
