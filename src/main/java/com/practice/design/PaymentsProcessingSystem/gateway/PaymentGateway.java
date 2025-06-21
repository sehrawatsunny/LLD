package com.practice.design.PaymentsProcessingSystem.gateway;

import com.practice.design.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;

/**
 * ✅ Interface Segregation Principle (ISP): Gateway clients only depend on a single method contract.
 * ✅ Open/Closed Principle (OCP): You can easily add new gateway implementations without modifying this interface.
 */
public interface PaymentGateway {
    /**
     * Processes a payment request through the implementing gateway.
     *
     * @param paymentRequest the complete payment request
     * @return the payment response containing transaction ID and status
     */
    PaymentResponse processPayment(PaymentRequest paymentRequest);
}