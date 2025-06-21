package com.practice.designpractice.PaymentsProcessingSystem.paymentmethod;


//PaymentMethod (Strategy Pattern)

import com.practice.designpractice.PaymentsProcessingSystem.gateway.PaymentGateway;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentResponse;

public interface PaymentMethod {
    boolean validate();

    String getName();

    PaymentResponse pay(PaymentRequest request, PaymentGateway gateway);
}

