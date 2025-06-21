package com.practice.designpractice.PaymentsProcessingSystem.processor;

import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentRequest;
import com.practice.designpractice.PaymentsProcessingSystem.core.PaymentResponse;
import com.practice.designpractice.PaymentsProcessingSystem.gateway.PaymentGateway;
import com.practice.designpractice.PaymentsProcessingSystem.paymentmethod.PaymentMethod;
import com.practice.designpractice.PaymentsProcessingSystem.routing.GatewayRouter;
import com.practice.designpractice.PaymentsProcessingSystem.transaction.ITransactionStore;
import com.practice.designpractice.PaymentsProcessingSystem.transaction.TransactionLogger;

/**
 * ✅ PaymentProcessor: Core component responsible for orchestrating the entire payment flow.
 *
 * 📌 Responsibilities:
 * - Handles the end-to-end processing of a payment request
 * - Ensures idempotency of requests
 * - Delegates payment execution to appropriate PaymentMethod & Gateway
 * - Persists transaction records via ITransactionStore
 * - Logs the transaction result
 *
 * ✅ SOLID Principles:
 * - SRP (Single Responsibility Principle): Only responsible for coordinating payment flow (not doing logging/gateway logic etc.)
 * - OCP (Open-Closed Principle): Can support new payment types or logging strategies without modifying this class
 * - DIP (Dependency Inversion Principle): Depends on abstractions like ITransactionStore, not concrete implementations
 */
public class PaymentProcessor {

    private final IdempotencyStore idempotencyStore;
    private final GatewayRouter gatewayRouter;
    private final TransactionLogger logger;
    private final ITransactionStore transactionStore;
    private final Validator validator;

    /**
     * Constructs the PaymentProcessor with all dependencies injected.
     *
     * @param idempotencyStore  Store to track if a request has already been processed
     * @param gatewayRouter     Responsible for returning correct PaymentGateway based on method type
     * @param logger            Responsible for logging transaction details
     * @param transactionStore  Store to persist transaction data (interface-based for flexibility)
     */
    public PaymentProcessor(IdempotencyStore idempotencyStore,
                            GatewayRouter gatewayRouter,
                            TransactionLogger logger,
                            ITransactionStore transactionStore,
                            Validator validator) {
        this.idempotencyStore = idempotencyStore;
        this.gatewayRouter = gatewayRouter;
        this.logger = logger;
        this.transactionStore = transactionStore;
        this.validator = validator;
    }

    /**
     * Processes a payment request by coordinating:
     * 1. Idempotency check
     * 2. Gateway routing
     * 3. Payment execution using strategy
     * 4. Transaction storage
     * 5. Logging
     *
     * @param request The payment request containing user, amount, currency, etc.
     * @param method  The payment method strategy (e.g., Card, UPI)
     * @return Final PaymentResponse (SUCCESS / FAILED etc.)
     */
    public PaymentResponse process(PaymentRequest request, PaymentMethod method) {
        if (!validator.validate(request)) {
            throw new IllegalArgumentException("❌ Invalid Payment Request");
        }
        // ✅ Idempotency check: Return cached response if same request was processed earlier
        if (idempotencyStore.hasProcessed(request.getIdempotencyKey())) {
            System.out.println("⚠️ Request already processed. Returning cached response.");
            return idempotencyStore.get(request.getIdempotencyKey());
        }

        // ✅ Gateway resolution: Get the right payment gateway (e.g., Razorpay, PayU) based on method type
        PaymentGateway gateway = gatewayRouter.getGateway(request.getPaymentMethod().getName());
        if (gateway == null) {
            throw new RuntimeException("Unsupported payment method: " + request.getPaymentMethod());
        }

        // ✅ Strategy execution: Execute the actual payment using the provided payment method
        PaymentResponse response = method.pay(request, gateway);

        // ✅ Persistence: Save transaction result to the transaction store
        transactionStore.save(response);

        // ✅ Idempotency registration: Mark this request as processed
        idempotencyStore.put(request.getIdempotencyKey(), response);

        // ✅ Logging: Log the transaction details for audit/debugging
        logger.log(request, response);

        return response;
    }
}
