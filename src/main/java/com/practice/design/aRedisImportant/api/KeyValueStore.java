package com.practice.design.aRedisImportant.api;


import java.util.List;

public interface KeyValueStore {
    void put(String key, String value);

    String get(String key);

    boolean remove(String key);

    List<String> search(String token);
}
