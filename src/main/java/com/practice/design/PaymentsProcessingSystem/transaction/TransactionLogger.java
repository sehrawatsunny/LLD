package com.practice.design.PaymentsProcessingSystem.transaction;

import com.practice.design.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;

public class TransactionLogger {

    public void log(PaymentRequest request, PaymentResponse response) {
        System.out.println("Transaction Log - User: " + request.getUserId() + ", Status: " + response.getStatus());
    }
}