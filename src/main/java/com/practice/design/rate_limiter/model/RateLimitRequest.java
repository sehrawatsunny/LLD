package com.practice.design.rate_limiter.model;

import lombok.Data;

@Data
public class RateLimitRequest {
    // The user identifier for which the rate limit is being checked
    private final String userId;
    // The API identifier for which the rate limit is being checked
    private final String apiId;
    public RateLimitRequest(String userId, String apiId) {
        this.userId = userId;
        this.apiId = apiId;
    }
}
