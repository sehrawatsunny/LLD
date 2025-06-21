package com.practice.designpractice.PaymentsProcessingSystem.processor;

import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentRequest;

public class Validator {
    public boolean validate(PaymentRequest request){
        return request.getAmount() > 0 && request.getCurrency()!=null && request.getPaymentMethod()!=null;
    }
}