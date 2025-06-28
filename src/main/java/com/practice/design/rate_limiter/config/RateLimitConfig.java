package com.practice.design.rate_limiter.config;

import lombok.Data;

@Data
public class RateLimitConfig {
    private final int limit;
    private final long windowInMillis;

    public RateLimitConfig(int limit, long windowInMillis) {
        this.limit = limit;
        this.windowInMillis = windowInMillis;
    }
}
