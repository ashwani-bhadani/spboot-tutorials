package com.tutorial.paypal.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Currency;

@Service
public class PaypalService {

    /**
     * Used For:
     * Configuring REST API calls to PayPal
     * Holding access token, client ID, secret, environment (sandbox/live)
     * Supports things like request ID for idempotency, configuration maps, etc.
     */
    private final APIContext apiContext;

    public PaypalService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    /**
     *
     * @param total
     * @param currency
     * @param method
     * @param intent
     * @param description
     * @param cancelUrl
     * @param successUrl
     * @return
     */
    public Payment createPayment( Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws PayPalRESTException {
        //Returns the Currency instance for an ISO 4217 code
        Currency rupees = Currency.getInstance(currency); //tested only for "INR"
        Amount amount = new Amount();
        amount.setCurrency(rupees.getCurrencyCode()); //retunrs "INR"
        //pass correct Locale like for US/India its 100.00 but in EU/Swiss it can be 100,00, we use dynamic to find locale, can define static value in properties too, set deciaml to 2 places
        amount.setTotal(String.format(Locale.forLanguageTag(rupees.getCurrencyCode()),"%.2f",total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        //within one payment we can have multiple transactions
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        //need to check payment type from card, wallet, etc
        Payer paymtType = new Payer();
        paymtType.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent); //like UPIIntent etc
        payment.setPayer(paymtType);
        payment.setTransactions(transactionList);

        //where to redirect in case of success/failure
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        payment.setRedirectUrls(redirectUrls);

        //TODO: add current system time with zone info to payment object
//        DateTimeFormatter formatter = DateTimeFormatter.
//        payment.setCreateTime(LocalDate.now().format());

        return payment.create(apiContext); //TODO: handle this error in better manner
    }

    //executing the payment
    public Payment executePayment( String paymentId, String payerId ) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }


}
