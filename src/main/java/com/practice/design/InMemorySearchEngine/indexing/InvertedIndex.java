package com.practice.design.InMemorySearchEngine.indexing;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Maintains an inverted index for fast word-to-document lookup.
 *
 * SOLID: Follows SRP - only handles index maintenance.
 * Maps word to set of docIds.
 */
public class InvertedIndex {
    private final Map<String, Set<Integer>> indexMap = new HashMap<>();

    public void add(String word , int docId){
//      indexMap.computeIfAbsent(word, k -> new HashSet<>()).add(docId);
        indexMap.putIfAbsent(word,new HashSet<>());
        indexMap.get(word).add(docId);
    }

    public Set<Integer> getMatchingDocuments(String word){
        return indexMap.getOrDefault(word,new HashSet<>());
    }
}