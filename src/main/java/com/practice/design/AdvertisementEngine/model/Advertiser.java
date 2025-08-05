package com.practice.design.AdvertisementEngine.model;

import java.util.concurrent.atomic.AtomicReference;
import java.math.BigDecimal;

public class Advertiser {
    private final String advertiserId;
    private final String name;
    private final AtomicReference<BigDecimal> budget;

    public Advertiser(String advertiserId, String name) {
        this.advertiserId = advertiserId;
        this.name = name;
        this.budget = new AtomicReference<>(BigDecimal.ZERO);
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget.get().doubleValue();
    }

    public void addBudget(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Budget amount cannot be negative");
        }
        budget.updateAndGet(current -> current.add(BigDecimal.valueOf(amount)));
    }

    public boolean deductBudget(double amount) {
        BigDecimal amt = BigDecimal.valueOf(amount);
        int retries = 0;
        while (true) {
            BigDecimal currentBudget = budget.get();
            if (currentBudget.compareTo(amt) < 0) {
                return false;
            }
            BigDecimal newBudget = currentBudget.subtract(amt);
            if (budget.compareAndSet(currentBudget, newBudget)) {
                return true;
            }
            retries++;
            if (retries > 10) {
                System.err.println("Budget deduction retry limit exceeded for advertiser " + advertiserId);
                return false;
            }
        }
    }

    public boolean hasSufficientBudget(double amount) {
        return budget.get().compareTo(BigDecimal.valueOf(amount)) >= 0;
    }
}