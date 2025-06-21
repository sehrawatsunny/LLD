package com.practice.designpractice.PaymentsProcessingSystem.paymentmethod;

import com.practice.designpractice.PaymentsProcessingSystem.gateway.PaymentGateway;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentResponse;

public class CardPaymentMethod implements PaymentMethod {
    private final String cardNumber;
    private final String expiry;
    private final String cvv;

    public CardPaymentMethod(String cardNumber, String expiry, String cvv) {
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    @Override
    public boolean validate() {
        return cardNumber != null && expiry != null && cvv != null && cardNumber.length() == 16;
    }

    public String getName(){
        return "CARD";
    }

    @Override
    public PaymentResponse pay(PaymentRequest request, PaymentGateway gateway) {
        // Card-specific logic could be added here
        return gateway.processPayment(request);
    }
}