package com.tutorial.asyncApp.apis;

import com.tutorial.asyncApp.model.Order;
import com.tutorial.asyncApp.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentFulfillmentService;

    //webhook callback URL
    @PostMapping("/paymentStatus")
    public ResponseEntity<String> paymentCallbackWebhook(
            @RequestBody(required = false) Order order
    ) throws InterruptedException {
        log.info("Checking payment status info from razorpay");
        paymentFulfillmentService.processPayment(order);
        return ResponseEntity.status(HttpStatus.OK).body("Payment received");
    }

}
