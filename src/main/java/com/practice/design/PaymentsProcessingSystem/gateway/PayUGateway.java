package com.practice.design.PaymentsProcessingSystem.gateway;

import com.practice.design.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;
import com.practice.design.PaymentsProcessingSystem.core.PaymentStatus;

public class PayUGateway implements PaymentGateway {
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        return new PaymentResponse(true, "payu_" + System.currentTimeMillis(), "Paid via PayU", PaymentStatus.SUCCESS);
    }
}

