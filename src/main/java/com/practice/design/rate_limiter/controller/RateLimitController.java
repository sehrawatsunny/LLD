package com.practice.design.rate_limiter.controller;

import com.practice.design.rate_limiter.config.RateLimitConfig;
import com.practice.design.rate_limiter.config.RateLimitConfigManager;
import com.practice.design.rate_limiter.core.RateLimiterStrategy;
import com.practice.design.rate_limiter.model.RateLimitRequest;

/**
 * RateLimitController is responsible for handling rate limit requests.
 * It uses a RateLimiterStrategy to apply rate limiting based on the provided configurations.
 */

public class RateLimitController {
    private final RateLimiterStrategy rateLimiterStrategy;

    private final RateLimitConfig defaultConfig;
    private final RateLimitConfigManager configManager;

    public RateLimitController(RateLimiterStrategy strategy, RateLimitConfig defaultConfig, RateLimitConfigManager configManager) {
        this.rateLimiterStrategy = strategy;
        this.defaultConfig = defaultConfig;
        this.configManager = configManager;
    }

    /**
     * Handles a rate limit request by applying the appropriate rate limiting strategy.
     * It retrieves the configuration for the request and applies the rate limit.
     *
     * @param request The rate limit request containing userId and apiId.
     */
    public void handleRequest(RateLimitRequest request) {
        RateLimitConfig config = configManager.getRateLimitConfig(request, defaultConfig);
        rateLimiterStrategy.applyRateLimit(request, config);
    }

}
