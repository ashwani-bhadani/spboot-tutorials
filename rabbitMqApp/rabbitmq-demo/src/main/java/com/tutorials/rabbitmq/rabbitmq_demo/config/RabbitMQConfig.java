package com.tutorials.rabbitmq.rabbitmq_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    //spring bean for rabbitmq queue using AMQP core
    @Bean
    public Queue queue() {
        log.info("MQ101: queueName " + queueName);
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange","test-subscriber-queue-dlx");
        return new Queue(queueName, true, false, false);
    }

    //defining spring bean for exchange from AMQP core
    @Bean
    public TopicExchange exchange() {
        log.info("MQ101: exchangeName " + exchangeName);
        return new TopicExchange(exchangeName);
    }

    //bind exchange to queue using routing key AMQP core package
    @Bean
    public Binding binding() {
        log.info("MQ101: routingKey " + routingKey);
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //below infrastructure beans get auto configure from starter
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin

}
