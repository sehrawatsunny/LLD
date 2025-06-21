package com.practice.design.PaymentsProcessingSystem.processor;

import com.practice.design.PaymentsProcessingSystem.core.PaymentRequest;

public class Validator {
    public boolean validate(PaymentRequest request){
        return request.getAmount() > 0 && request.getCurrency()!=null && request.getPaymentMethod()!=null;
    }
}