package com.practice.design.PaymentsProcessingSystem.processor;

import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ✅ Single Responsibility Principle (SRP):
 * Responsible solely for managing idempotency keys and their corresponding responses.
 *
 * ✅ Open/Closed Principle (OCP):
 * Can be extended with expiration policies, storage backends (e.g., Redis) without modifying existing code.
 *
 * ✅ Thread-Safe:
 * Uses ConcurrentHashMap to allow concurrent access in multi-threaded payment flows.
 *
 * 💡 Why it’s needed:
 * Prevents duplicate payment processing if the same request is retried (e.g., due to timeout or network retry).
 */
public class IdempotencyStore {

    // Key: idempotency key (e.g., request hash, transaction ID)
    // Value: previously processed response
    private final ConcurrentHashMap<String, PaymentResponse> store = new ConcurrentHashMap<>();

    /**
     * Checks if a payment with the given idempotency key has already been processed.
     *
     * @param key unique identifier for the request (e.g., UUID or hash of user+order)
     * @return true if the response already exists
     */
    public boolean hasProcessed(String key) {
        return store.containsKey(key);
    }

    /**
     * Retrieves the stored response for a previously processed request.
     *
     * @param key the idempotency key
     * @return the PaymentResponse if present; null otherwise
     */
    public PaymentResponse get(String key) {
        return store.get(key);
    }

    /**
     * Stores the response for a new successful payment request.
     *
     * @param key the idempotency key
     * @param response the payment response to store
     */
    public void put(String key, PaymentResponse response) {
        store.put(key, response);
    }
}
