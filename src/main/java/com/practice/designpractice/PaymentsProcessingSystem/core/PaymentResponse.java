package com.practice.designpractice.PaymentsProcessingSystem.core;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PaymentResponse {
    private final boolean success;
    private final String transactionId;
    private final String message;
    private final PaymentStatus status;
}