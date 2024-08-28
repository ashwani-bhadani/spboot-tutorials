package com.tutorials.rabbitmq.rabbitmq_demo.controller;

import com.tutorials.rabbitmq.rabbitmq_demo.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(
            @RequestParam("message") String message
    ) {
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to queue!");
    }
}
