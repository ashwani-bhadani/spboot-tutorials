package com.tutorials.rabbitmq.rabbitmq_demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = {"$rabbitmq.queue.name"})
    public void consume(String message) {
        log.info(String.format("Message read -> ", message));
    }

}
