package com.practice.design.aRedisImportant.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InvertedIndex {
    private final ConcurrentHashMap<String, Set<String >> index = new ConcurrentHashMap<>();

    private List<String> tokenize(String content){
        String[] tokens = content.toLowerCase().split("\\s+");

        return Arrays.stream(tokens).collect(Collectors.toList());
    }

    public void add(String key , String value){
        // Tokenize the value.
        List<String> tokens = tokenize(value);
        for(String token : tokens){
            index.computeIfAbsent(token, k-> ConcurrentHashMap.newKeySet()).add(key);
        }
    }

    public void remove(String key, String value){
        List<String> tokens = tokenize(value);
        for(String token : tokens){
            Set<String> keySet = index.get(token);
            keySet.remove(key);
        }
    }

    public List<String> search(String token){
        Set<String> keySet = index.getOrDefault(token.toLowerCase(),Collections.emptySet());
        return new ArrayList<>(keySet);
    }

}
