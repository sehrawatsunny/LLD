package com.practice.design.rate_limiter.storage;

public interface IRateLimitStore {
    int incrementAndGet(String key, long currentWindowStartTime);
}
