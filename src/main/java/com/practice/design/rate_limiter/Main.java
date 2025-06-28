package com.practice.design.rate_limiter;


import com.practice.design.rate_limiter.config.RateLimitConfig;
import com.practice.design.rate_limiter.config.RateLimitConfigManager;
import com.practice.design.rate_limiter.controller.RateLimitController;
import com.practice.design.rate_limiter.core.FixedWindowRateLimiter;
import com.practice.design.rate_limiter.core.RateLimiterStrategy;
import com.practice.design.rate_limiter.exceptions.RateLimitExceedException;
import com.practice.design.rate_limiter.model.RateLimitRequest;
import com.practice.design.rate_limiter.storage.IRateLimitStore;
import com.practice.design.rate_limiter.storage.InMemoryRateLimitStore;

public class Main {
    public static void main(String[] args) {
        IRateLimitStore store = new InMemoryRateLimitStore();
        RateLimiterStrategy strategy = new FixedWindowRateLimiter(store);

        // Default rate limit config: 5 requests per 10 seconds
        // This can be overridden by user-specific configurations
        RateLimitConfig defaultConfig = new RateLimitConfig(5, 10_000); // 5 reqs / 10s

        // Initialize the RateLimitConfigManager to manage user-specific rate limits
        RateLimitConfigManager configManager = new RateLimitConfigManager();

        // Create the RateLimitController with the strategy, default config, and config manager
        RateLimitController controller = new RateLimitController(strategy, defaultConfig, configManager);

        // ✅ Set custom rate limit for user123/apiX
        // 10000 milliseconds = 10 seconds
        configManager.setRateLimit("user123", "apiX", 3, 5000);

        // Simulate requests...
        RateLimitRequest req = new RateLimitRequest("user123", "apiX");
        for (int i = 0; i < 30; i++) {
            try {
                controller.handleRequest(req);
                System.out.println("✅ Allowed");
            } catch (RateLimitExceedException e) {
                System.out.println("❌ Blocked: " + e.getMessage());
            }

            try {
                Thread.sleep(250); // Sleep 250ms per request
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
