package com.tutorial.asyncApp.service;

import com.tutorial.asyncApp.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class OrderFulfillmentService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    /* All Required process */
    /*
      1. Inventory service check order availability
      2. Process payment for order
      3. Notify to the user
      3. Assign to vendor
      4. packaging
      5. assign delivery partner
      6. assign trailer
      7. dispatch product
      **/

    public Order processOrder(Order order) throws InterruptedException {
        order.setTrackingId(UUID.randomUUID().toString());
        if(inventoryService.checkProductAvailability(order.getProductId())){
            //TODO: handle some exception here
            paymentService.processPayment(order);
        } else {
            throw new RuntimeException("Internal Server Error: Try again later!");
        }
        return order;
    }

    @Async("AsyncOrderExecutor") //AsyncOrderExecutor bean config to be used, @return must be void/CompleteableFuture
    public void notifyUser(Order order) throws InterruptedException {
        Thread.sleep(4000L);
        log.info("Notified the user " + Thread.currentThread().getName());
    }

    @Async("AsyncOrderExecutor")
    public void assignVendor(Order order) throws InterruptedException {
        Thread.sleep(7000L);
        log.info("Vendor assigned to the users' package " + Thread.currentThread().getName());
    }

    @Async("AsyncOrderExecutor")
    public void packaging(Order order) throws InterruptedException {
        Thread.sleep(7000L);
        log.info("Order packaged" + Thread.currentThread().getName());
    }

    @Async("AsyncOrderExecutor")
    public void assignDeliveryPartner(Order order) throws InterruptedException {
        Thread.sleep(7000L);
        log.info("Delivery partner for last mile delivery assigned" + Thread.currentThread().getName());
    }

    @Async("AsyncOrderExecutor")
    public void assignTrailerAndDispatch(Order order) throws InterruptedException {
        Thread.sleep(7000L);
        log.info("Trailer loaded & dispatched for delivery" + Thread.currentThread().getName());
    }

}
