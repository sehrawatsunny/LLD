package com.practice.design.PaymentsProcessingSystem.transaction;

import com.practice.design.PaymentsProcessingSystem.core.PaymentResponse;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * ✅ Single Responsibility Principle (SRP):
 * This class only handles storing and managing payment transactions.
 *
 * ✅ Open/Closed Principle (OCP):
 * It can be extended later to support persistent storage (e.g., database, file system) without modification.
 *
 * ✅ Thread Safety:
 * Uses Collections.synchronizedList to ensure safe concurrent access.
 * This ensures that multiple threads writing/reading from the list won't cause inconsistent state.
 */
/**
 * ✅ SRP: Handles in-memory storage of transactions only.
 * ✅ OCP: Can be replaced with another implementation like DatabaseTransactionStore.
 * ✅ DIP: Implements the ITransactionStore interface for abstraction.
 */
public class InMemoryTransactionStore implements ITransactionStore {

    // Thread-safe wrapper around ArrayList to ensure concurrency support
    private final List<PaymentResponse> transactions = Collections.synchronizedList(new ArrayList<>());

    /**
     * Saves a payment response to the transaction store.
     *
     * @param response the PaymentResponse to be saved
     */
    public void save(PaymentResponse response) {
        transactions.add(response);
    }

    /**
     * Optional method: retrieve a snapshot of all transactions.
     * Can be used for auditing, querying, etc.
     */
    public List<PaymentResponse> getAllTransactions() {
        // Return a copy to avoid concurrent modification during iteration
        synchronized (transactions) {
            return new ArrayList<>(transactions);
        }
    }
}
