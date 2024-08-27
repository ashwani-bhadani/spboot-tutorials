package com.pubSubExample.kafkaProducer.dto;

import lombok.Data;

@Data
public class Customer {
    private int custId;
    private String name;
    private String email;
    private String contactNo;

}
