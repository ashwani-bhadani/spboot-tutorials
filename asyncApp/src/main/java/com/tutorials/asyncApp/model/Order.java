package com.tutorial.asyncApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private int productId;
    private String name;
    private String productCategory;
    private int quantity;
    private double price;
    private String trackingId;
}
