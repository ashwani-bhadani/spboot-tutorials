package com.tutorial.paypal.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.tutorial.paypal.service.PaypalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller //not @RestController because we are using MVC & ThymeLeaf
public class PaypalController {

    private static final Logger log = LoggerFactory.getLogger(PaypalController.class);

    private final PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @GetMapping("/") //return paymentHome.html page
    public String homePage() {
        return "paymentHome";
    } //create view to be rendered by this api in resources templates

    //payment creation, uses servlet.RedirectView for model & view processing
    //TODO: move payment to request mapping class level & add AOP & use protobufs intstead of JSON for low latency & high throughput
    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestParam("paymethod") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description
    ) {
        try {
            String cancelUrl = "http://localhost:8085/payment/cancel";
            String successUrl = "http://localhost:8085/payment/success";
//to prevent bad_request error keep below params -> details":[{"field":"/intent","location":"body","issue":"sales is invalid value. Supported values are SALE, AUTHORIZE, ORDER, NONE"}]}
//Response code: 400	Error response: {"name":"CURRENCY_NOT_ALLOWED","message":"Currency is not supported"
            Payment paymentAttempt =  paypalService.createPayment(100.0,currency,method,"sale",description, cancelUrl, successUrl);
            //extract approval link

//            paymentAttempt.getLinks().stream()
            for(Links link : paymentAttempt.getLinks()) {
                if("approval_url".equalsIgnoreCase(link.getRel()) ){
                    return new RedirectView(link.getHref());
                }
            }

        } catch (PayPalRESTException pprex) {
            if (log.isErrorEnabled()) log.error("Error in processing payment : {}", pprex.getMessage());
//            return new RedirectView("/payment/paypal_error");
        } catch (Exception ex) {
            if (log.isErrorEnabled()) log.error("Error in processing payment & is unrecoverable : {}", ex.getMessage());
//            return new RedirectView("/payment/ise_error");
        }

        //TODO: have try catch block inside catch ppre & have diff redirect Urls for unrecoverable error & ppre error
        return new RedirectView("/payment/error");
    }

//sample -> http://localhost:8085/payment/success?paymentId=PAYID-M75NFBY4UA40381UB1680600&token=EC-26T62597T0590552B&PayerID=66APWBAYYQJEN
    @GetMapping("/payment/success") //both params provided by paypal
    public String paymentSuccess(@RequestParam("paymentId") String paymnetId, @RequestParam("PayerID") String payerId) {
        try {
            Payment paymtExecAttempt = paypalService.executePayment(paymnetId, payerId);
            if("approval_url".equalsIgnoreCase(paymtExecAttempt.getState())){
                return "paymentSuccess";
            }

        } catch (PayPalRESTException pprex) {
            if (log.isErrorEnabled()) log.error("Error in processing payment : {}", pprex.getMessage());
//            return new RedirectView("/payment/paypal_error");
        } catch (Exception ex) {
            if (log.isErrorEnabled()) log.error("Error in processing payment & is unrecoverable : {}", ex.getMessage());
//            return new RedirectView("/payment/ise_error");
        }

        return "paymentSuccess"; //TODO: handle error
    }

    @GetMapping("/payment/cancel")
    public String paymentCancellation() {
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError() {
        return "paymentError";
    }

}
