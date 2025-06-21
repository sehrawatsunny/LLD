package com.practice.design.PaymentsProcessingSystem.core;

import com.practice.design.PaymentsProcessingSystem.paymentmethod.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ✅ PaymentRequest
 *
 * 📌 Responsibilities:
 * - Encapsulates a payment attempt made by a user.
 * - Carries all required details to process a payment.
 *
 * ✅ SOLID Principles:
 * - SRP: Pure data holder for a single payment attempt.
 * - OCP: Can be extended to support new fields (e.g., billing address) without modifying logic.
 * - LSP: Can be passed to all components expecting a valid payment request.
 */
@Data // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor
public class PaymentRequest {
    private final String userId;               // Who is paying
    private final double amount;               // How much
    private final String currency;             // Currency type (e.g., INR, USD)
    private final String idempotencyKey;       // To ensure idempotent requests
    private final PaymentMethod paymentMethod; // Strategy object (CardPayment, UpiPayment)
}
