package com.practice.design.AdvertisementEngine.exception;

public class InsufficientBudgetException extends AdvertisementEngineException {
    public InsufficientBudgetException(String advertiserId, double required, double available) {
        super(String.format("Insufficient budget for advertiser %s. Required: %.2f, Available: %.2f",
                advertiserId, required, available));
    }
}