package com.pubSubExample.kafkaProducer.controller;

import com.pubSubExample.kafkaProducer.dto.Customer;
import com.pubSubExample.kafkaProducer.service.KafkaMessagePublisher;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try{
            int i =0;
            while(i<=10000) {
                publisher.sendMessageToTopic(message);
                i++;
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Message has been published!");
        } catch ( Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in EventController::publishMessage!");
        }
    }

    @GetMapping("/publish")
    public ResponseEntity<?> publishCustomer(@RequestBody Customer customer) {
        try{
            publisher.sendObjectsToTopic(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body("Message has been published!");
        } catch ( Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in EventController::publishMessage!");
        }
    }

}
