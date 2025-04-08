package com.tutorial.asyncApp.apis;

import com.tutorial.asyncApp.model.Order;
import com.tutorial.asyncApp.service.OrderFulfillmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderFulfillmentService orderService;

    @PostMapping
    public ResponseEntity<Order> processOrder(@RequestBody Order order) throws InterruptedException {
        orderService.processOrder(order); //only in sync. mode, rest below are async.
                orderService.notifyUser(order);
                orderService.assignVendor(order);
                orderService.packaging(order);
                orderService.assignTrailerAndDispatch(order);
                orderService.assignDeliveryPartner(order);
        return ResponseEntity.ok(order);
    }

}
