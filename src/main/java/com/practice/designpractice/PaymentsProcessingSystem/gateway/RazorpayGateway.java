package com.practice.designpractice.PaymentsProcessingSystem.gateway;

import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentResponse;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentStatus;

public class RazorpayGateway implements PaymentGateway {

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        // Mocking gateway logic
        return new PaymentResponse(true, "rzp_" + System.currentTimeMillis(), "Paid via Razorpay", PaymentStatus.SUCCESS);
    }
}
