package com.pubSubExample.kafkaConsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageListener {

    @KafkaListener(topics = "kafka-tutorials")
    private void consume(String message) {
        log.info("Consumer polling and consuming messages!");
    }

}
