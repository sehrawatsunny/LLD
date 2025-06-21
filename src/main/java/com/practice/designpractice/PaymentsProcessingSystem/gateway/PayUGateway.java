package com.practice.designpractice.PaymentsProcessingSystem.gateway;

import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentResponse;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentStatus;

public class PayUGateway implements PaymentGateway {
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        return new PaymentResponse(true, "payu_" + System.currentTimeMillis(), "Paid via PayU", PaymentStatus.SUCCESS);
    }
}

