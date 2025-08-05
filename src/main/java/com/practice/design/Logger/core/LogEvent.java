package com.practice.design.Logger.core;

import java.time.Instant;

public record LogEvent(LogLevel level,
                       String  message,
                       Instant timestamp,
                       Throwable cause,
                       String  thread) {

    public LogEvent(LogLevel level, String message) {
        this(level, message, Instant.now(), null, Thread.currentThread().getName());
    }
}