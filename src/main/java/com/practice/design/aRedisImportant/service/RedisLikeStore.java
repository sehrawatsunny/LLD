package com.practice.design.aRedisImportant.service;


import com.practice.design.aRedisImportant.api.KeyValueStore;
import com.practice.design.aRedisImportant.core.InMemoryStore;
import com.practice.design.aRedisImportant.core.InvertedIndex;
import com.practice.design.aRedisImportant.core.LruCache;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * RedisLikeStore is the main service class that:
 * - Stores data in a thread-safe in-memory store (using ConcurrentHashMap)
 * - Maintains an LRU cache for hot keys
 * - Supports token-based search using an inverted index
 * - Ensures thread safety using ReentrantReadWriteLock
 */
public class RedisLikeStore implements KeyValueStore {

    // Core components: durable store, searchable index, and cache
    private final InMemoryStore store = new InMemoryStore();
    private final InvertedIndex index = new InvertedIndex();
    private final LruCache cache;

    // Global read-write lock for thread safety
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Constructor initializes the LRU cache with given capacity.
     */
    public RedisLikeStore(int cacheCapacity) {
        this.cache = new LruCache(cacheCapacity);
    }

    /**
     * Inserts or updates a key-value pair.
     * Updates all components:
     * - InMemory store (main source of truth)
     * - Inverted index (for token-based search)
     * - LRU cache (for fast get access)
     */
    @Override
    public void put(String key, String value) {
        lock.writeLock().lock(); // acquire write lock to ensure atomic update
        try {
            // Put value in main store and retrieve old value (if any)
            String oldValue = store.put(key, value);

            // If key existed before, remove its old tokens from index
            if (oldValue != null) {
                index.remove(key, oldValue);
            }

            // Add new tokens from the new value to index
            index.add(key, value);

            // Update LRU cache with new value
            cache.put(key, value);

        } finally {
            lock.writeLock().unlock(); // always release lock
        }
    }

    /**
     * Retrieves the value for a given key.
     * - First checks LRU cache for hot key
     * - Falls back to main store if cache miss
     * - On cache miss but successful store hit, promotes the key to cache
     */
    @Override
    public String get(String key) {
        lock.readLock().lock(); // multiple gets can proceed concurrently
        try {
            // Try LRU cache first
            String value = cache.get(key);
            if (value != null) return value;

            // Fallback: Get from store and cache it if found
            value = store.get(key);
            if (value != null) {
                cache.put(key, value); // promote to cache
            }
            return value;
        } finally {
            lock.readLock().unlock(); // release read lock
        }
    }

    /**
     * Removes a key-value pair.
     * Updates all components:
     * - Deletes from store
     * - Removes key from index (for all tokens in old value)
     * - Removes from cache
     */
    @Override
    public boolean remove(String key) {
        lock.writeLock().lock(); // exclusive write operation
        try {
            // Remove from store, and get the old value
            String oldValue = store.remove(key);
            if (oldValue == null) {
                return false; // key not found
            }

            // Clean up index and cache
            index.remove(key, oldValue);
            cache.remove(key);
            return true;

        } finally {
            lock.writeLock().unlock(); // always unlock
        }
    }

    /**
     * Searches for all keys whose values contain the given token.
     * - Uses the inverted index (token ➝ Set of keys)
     * - Tokenization is case-insensitive and split by non-alphanumeric
     * - Read-only operation, allows concurrent access
     */
    @Override
    public List<String> search(String token) {
        lock.readLock().lock(); // safe for concurrent readers
        try {
            return index.search(token); // O(1) hash lookup on index
        } finally {
            lock.readLock().unlock(); // release read lock
        }
    }

    /** Expose cache state for debugging/demo purposes */
    public void printCache() {
        cache.printCache();     // delegate to the LruCache’s own printer
    }
}
