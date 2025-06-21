package com.practice.designpractice.PaymentsProcessingSystem.transaction;

import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentResponse;

public class TransactionLogger {

    public void log(PaymentRequest request, PaymentResponse response) {
        System.out.println("Transaction Log - User: " + request.getUserId() + ", Status: " + response.getStatus());
    }
}