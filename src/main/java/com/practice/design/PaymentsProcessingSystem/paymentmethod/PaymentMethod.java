package com.practice.design.PaymentsProcessingSystem.paymentmethod;


//PaymentMethod (Strategy Pattern)

import com.practice.design.PaymentsProcessingSystem.gateway.PaymentGateway;
import com.practice.design.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;

public interface PaymentMethod {
    boolean validate();

    String getName();

    PaymentResponse pay(PaymentRequest request, PaymentGateway gateway);
}

