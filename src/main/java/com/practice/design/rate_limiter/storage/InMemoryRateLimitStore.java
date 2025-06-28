package com.practice.design.rate_limiter.storage;

import java.util.concurrent.ConcurrentHashMap;

/**
 * An in-memory implementation of IRateLimitStore using Fixed Window strategy.
 * <p>
 * ✅ SOLID:
 * - SRP: Handles only storage and counting logic.
 * - OCP: Can be extended or replaced with Redis-based store.
 * - DIP: Interfaced via IRateLimitStore.
 */
public class InMemoryRateLimitStore implements IRateLimitStore {

    /**
     * A thread-safe store for rate limit windows.
     * Maps a key (userId:api) to a Window object containing count and start time.
     */
    private final ConcurrentHashMap<String, Window> store = new ConcurrentHashMap<>();

    /**
     * Increments the count for a given key in a fixed window manner.
     * If the key does not exist or the window has changed, it creates a new Window.
     *
     * @param key                  The key for which the count is to be incremented.
     * @param currentWindowStartTime The start time of the current window.
     * @return The updated count for the key.
     */
    @Override
    public int incrementAndGet(String key, long currentWindowStartTime) {
        // Increment the count for the given key in a fixed window manner
        Window window = store.compute(key, (k, existingWindow) -> {
            if (existingWindow == null || existingWindow.start != currentWindowStartTime) {
                // New window: reset count to 1
                return new Window(currentWindowStartTime);
            } else {
                // Same window: increment count
                existingWindow.count++;
                return existingWindow;
            }
        });
        return window.count;
//        Window existingWindow = store.get(key);
//        if (existingWindow == null || existingWindow.start != currentWindowStartTime) {
//            // New window: reset count to 1
//            existingWindow = new Window(currentWindowStartTime);
//            existingWindow.count = 1;
//            store.put(key, existingWindow);
//        } else {
//            // Same window: increment
//            existingWindow.count++;
//        }
    }
    private static class Window {
        int count;
        long start;

        public Window(long start) {
            this.start = start;
            this.count = 1; // Start with count 1 for the new window
        }
    }
}
