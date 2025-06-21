package com.practice.designpractice.PaymentsProcessingSystem.transaction;

import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentResponse;

public interface ITransactionStore {
    void save(PaymentResponse response);
}
