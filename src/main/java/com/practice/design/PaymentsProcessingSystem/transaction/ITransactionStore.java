package com.practice.design.PaymentsProcessingSystem.transaction;

import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;

public interface ITransactionStore {
    void save(PaymentResponse response);
}
