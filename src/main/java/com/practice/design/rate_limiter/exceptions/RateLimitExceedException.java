package com.practice.design.rate_limiter.exceptions;

import lombok.Data;

@Data
public class RateLimitExceedException extends RuntimeException {
    private final String userId;
    private final String apiId;

    public RateLimitExceedException(String userId, String apiId) {
        super("Rate limit exceeded for user: " + userId + " on API: " + apiId);
        this.userId = userId;
        this.apiId = apiId;
    }
}
