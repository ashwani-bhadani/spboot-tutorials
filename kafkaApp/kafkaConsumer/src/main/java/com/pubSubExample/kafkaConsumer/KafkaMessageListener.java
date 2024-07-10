package com.pubSubExample.kafkaConsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageListener {

//    M-2 of group.id apart from config class: @KafkaListener(topics = "my-topic", groupId = "my-consumer-group")
//    ensure zookeeper & kafka-server are live
    @KafkaListener(topics = "kafka-tutorials-2", groupId = "my-consumer-group")
    private void consume(String message) {
        log.info("Consumer polling and consuming messages!");
    }

}
