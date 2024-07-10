package com.pubSubExample.kafkaProducer.service;

import com.pubSubExample.kafkaProducer.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-tutorials-2", message);
//       handle results async using callback implementation
        future.whenComplete((result, exception)->{
            if(exception == null) {
                log.info("Sent message = [ " + message + " ] with offset = [ " + result.getRecordMetadata().offset() + " ]");
            } else {
                log.info("Unable to send message = [ " + message + " ] due to error :: " + exception.getMessage());
            }
        });
    }

    public void sendObjectsToTopic(Customer customer) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-object-events", customer);
        future.whenComplete((result, exception)->{
            if(exception == null) {
                log.info("Sent message = [ " + customer.toString() + " ] with offset = [ " + result.getRecordMetadata().offset() + " ]");
            } else {
                log.info("Unable to send message = [ " + customer.toString() + " ] due to error :: " + exception.getMessage());
            }
        });
    }
}
