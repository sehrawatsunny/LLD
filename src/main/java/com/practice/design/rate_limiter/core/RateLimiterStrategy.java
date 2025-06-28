package com.practice.design.rate_limiter.core;

import com.practice.design.rate_limiter.config.RateLimitConfig;
import com.practice.design.rate_limiter.model.RateLimitRequest;

public interface RateLimiterStrategy {
    void applyRateLimit(RateLimitRequest request, RateLimitConfig config);
}
