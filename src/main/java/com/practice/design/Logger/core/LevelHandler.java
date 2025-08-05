package com.practice.design.Logger.core;

import com.practice.design.Logger.observer.SinkSubject;

/**
 * Abstract base class in the Chain of Responsibility pattern.
 * Each handler processes a specific LogLevel (e.g., INFO, DEBUG, ERROR).
 */
public class LevelHandler {

    protected LevelHandler next;          // Next handler in the chain
    protected final LogLevel myLevel;     // This handler’s level (e.g., INFO)
    protected final SinkSubject subject;  // Central sink dispatcher

    /**
     * Constructor takes the log level this handler is responsible for,
     * and the shared SinkSubject for dispatching logs.
     */
    public LevelHandler(LogLevel myLevel, SinkSubject subject) {
        this.myLevel = myLevel;
        this.subject = subject;
    }

    /**
     * Links the next handler in the chain.
     * Returns the next handler to allow method chaining.
     */
    public LevelHandler setNext(LevelHandler next) {
        this.next = next;
        return next;
    }

    /**
     * Core logic: check if this handler should process the log.
     * If yes → publish to sinks via SinkSubject.
     * Always pass to next handler (if any) to allow multiple handlers to process the same message.
     */
    public void handle(LogEvent event) {
        // If the event matches this handler’s level, process it
        if (event.level().equals(myLevel)) {
            subject.publish(event);
        }

        // Forward to next handler in the chain
        if (next != null) {
            next.handle(event);
        }
    }
}
