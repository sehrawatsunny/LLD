package com.practice.design.rate_limiter.config;

import com.practice.design.rate_limiter.model.RateLimitRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ✅ SRP: Manages rate limit configurations for users/apis.
 * ✅ OCP: Can be extended for global/user-level API overrides.
 * ✅ Thread-safe: Uses ConcurrentHashMap.
 */
public class RateLimitConfigManager {

    // Maps "userId:apiId" → RateLimitConfig
    private final ConcurrentHashMap<String, RateLimitConfig> configMap = new ConcurrentHashMap<>();

    /**
     * Set or update a rate limit config for a user & API.
     */
    public void setRateLimit(String userId, String apiId, int limit, long windowMillis) {
        String key = userId + ":" + apiId;
        configMap.put(key, new RateLimitConfig(limit, windowMillis));
    }

    /**
     * Returns the config for this request. If not found, fallback to default.
     */
    public RateLimitConfig getRateLimitConfig(RateLimitRequest request, RateLimitConfig defaultConfig) {
        String key = request.getUserId() + ":" + request.getApiId();
        // If we have set config for that user, return it
        // else , return default config.
        return configMap.getOrDefault(key, defaultConfig);
    }
}