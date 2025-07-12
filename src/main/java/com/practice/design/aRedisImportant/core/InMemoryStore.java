package com.practice.design.aRedisImportant.core;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStore {
    private final ConcurrentHashMap<String, String> store = new ConcurrentHashMap<>();

    public String put(String key, String value){
        // Returns old value , if key already exists.
        // If key doesn't exists, return null.
        return store.put(key, value);
    }
    public String get(String key){
        return store.get(key);
    }
    public String remove(String key){
        return store.remove(key);
    }
}
