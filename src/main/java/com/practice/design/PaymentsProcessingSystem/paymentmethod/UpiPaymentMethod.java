package com.practice.design.PaymentsProcessingSystem.paymentmethod;

import com.practice.design.PaymentsProcessingSystem.gateway.PaymentGateway;
import com.practice.design.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;

public class UpiPaymentMethod implements PaymentMethod {
    private final String upiId;

    public UpiPaymentMethod(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean validate() {
        return upiId != null && upiId.contains("@");
    }
    @Override
    public PaymentResponse pay(PaymentRequest request, PaymentGateway gateway) {
        // UPI-specific logic could be added here
        return gateway.processPayment(request);
    }
    @Override
    public String getName(){
        return "UPI";
    }
}
