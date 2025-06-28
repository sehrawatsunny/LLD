package com.practice.design.rate_limiter.core;

import com.practice.design.rate_limiter.config.RateLimitConfig;
import com.practice.design.rate_limiter.exceptions.RateLimitExceedException;
import com.practice.design.rate_limiter.model.RateLimitRequest;
import com.practice.design.rate_limiter.storage.IRateLimitStore;

public class FixedWindowRateLimiter implements RateLimiterStrategy{

    private final IRateLimitStore rateLimitStore;

    public FixedWindowRateLimiter(IRateLimitStore rateLimitStore) {
        this.rateLimitStore = rateLimitStore;
    }

    @Override
    public void applyRateLimit(RateLimitRequest request, RateLimitConfig config) {
        String key = request.getUserId() + ":" + request.getApiId();
        long currentWindowStart = System.currentTimeMillis() / config.getWindowInMillis();

        int currentCount = rateLimitStore.incrementAndGet(key, currentWindowStart);

        if (currentCount > config.getLimit()) {
            throw new RateLimitExceedException(request.getUserId(),request.getApiId());
        }
    }

}
