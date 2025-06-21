package com.practice.design.PaymentsProcessingSystem.controller;

import com.practice.design.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;
import com.practice.design.PaymentsProcessingSystem.gateway.PayUGateway;
import com.practice.design.PaymentsProcessingSystem.gateway.RazorpayGateway;
import com.practice.design.PaymentsProcessingSystem.paymentmethod.CardPaymentMethod;
import com.practice.design.PaymentsProcessingSystem.paymentmethod.UpiPaymentMethod;
import com.practice.design.PaymentsProcessingSystem.processor.IdempotencyStore;
import com.practice.design.PaymentsProcessingSystem.processor.PaymentProcessor;
import com.practice.design.PaymentsProcessingSystem.processor.Validator;
import com.practice.design.PaymentsProcessingSystem.routing.GatewayRouter;
import com.practice.design.PaymentsProcessingSystem.transaction.ITransactionStore;
import com.practice.design.PaymentsProcessingSystem.transaction.InMemoryTransactionStore;
import com.practice.design.PaymentsProcessingSystem.transaction.TransactionLogger;

/**
 * ✅ PaymentController (formerly Main)
 * <p>
 * 📌 Responsibilities:
 * - Acts as the entry point / controller for simulating payment requests.
 * - Demonstrates payment processing with different payment methods and idempotency handling.
 * <p>
 * ✅ SOLID Principles:
 * - SRP: Handles orchestration logic for demonstration only (not mixed with business logic).
 * - DIP: Uses abstractions like ITransactionStore and PaymentMethod.
 */
public class PaymentController {
    public static void main(String[] args) {
        // 🔧 Setup: Configure dependencies and register gateways
        GatewayRouter gatewayRouter = new GatewayRouter();
        gatewayRouter.register("CARD", new RazorpayGateway());
        gatewayRouter.register("UPI", new PayUGateway());

        IdempotencyStore idempotencyStore = new IdempotencyStore();
        TransactionLogger logger = new TransactionLogger();
        ITransactionStore transactionStore = new InMemoryTransactionStore();


        Validator validator = new Validator();
        // 💳 Payment processor with all required components injected
        PaymentProcessor processor = new PaymentProcessor(
                idempotencyStore,
                gatewayRouter,
                logger,
                transactionStore,
                validator
        );
        // 3. Request #1: Card payment
        PaymentRequest request1 = new PaymentRequest(
                "user123",
                500.0,
                "INR",
                "idemp-001",
                new CardPaymentMethod("5241", "03/31", "123") // Strategy object
        );
        PaymentResponse response1 = processor.process(request1, request1.getPaymentMethod());
        System.out.println("Response1: " + response1);

        // 4. Request #2: Duplicate of request #1 (should return cached response)
        PaymentResponse response2 = processor.process(request1, request1.getPaymentMethod());
        System.out.println("Response2 (cached): " + response2);

        // 5. Request #3: UPI payment
        PaymentRequest request2 = new PaymentRequest(
                "user456",
                300.0,
                "INR",
                "idemp-002",
                new UpiPaymentMethod("aupiid@ptsbi") // Strategy object
        );
        PaymentResponse response3 = processor.process(request2, request2.getPaymentMethod());
        System.out.println("Response3: " + response3);
    }
}