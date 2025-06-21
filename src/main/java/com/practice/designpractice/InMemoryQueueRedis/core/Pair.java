package com.practice.designpractice.InMemoryQueueRedis.core;

/**
 * Generic Pair class to hold key-value pairs.
 * Used for attribute key-value pairs in put operations.
 */
public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

//    @Override
//    public String toString() {
//        return key + ": " + value;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (!(obj instanceof Pair)) return false;
//        Pair<?, ?> that = (Pair<?, ?>) obj;
//        return key.equals(that.key) && value.equals(that.value);
//    }
//
//    @Override
//    public int hashCode() {
//        return 31 * key.hashCode() + value.hashCode();
//    }
}