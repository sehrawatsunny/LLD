package com.practice.designpractice.InMemorySearchEngine.indexing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores word frequencies per document.
 * Useful for ranking and statistics.
 * SOLID: Follows SRP - tracks frequency of words per document
 * key of map -> docId
 * value of map -> word -> frequency mapping.
 */
public class WordFrequencyStore {
    private final HashMap<Integer, Map<String,Integer>> frequencyMap = new HashMap<>();

    /**
     * Adds word frequencies for a given document.
     * Stores the frequency count of each tokenized word in the internal map.
     *
     * @param docId ID of the document
     * @param tokenizedWords list of words extracted from the document
     */
    public void addFrequencies(int docId , List<String> tokenizedWords){
        // Map<String, Integer> freqWordInDoc = frequencies.computeIfAbsent(docId, k -> new HashMap<>());
        frequencyMap.putIfAbsent(docId,new HashMap<>());
        Map<String,Integer> freqWordInDoc = frequencyMap.get(docId);

        for(String tokenWord : tokenizedWords){
            freqWordInDoc.put(tokenWord,freqWordInDoc.getOrDefault(tokenWord,0)+1);
        }

        frequencyMap.put(docId,freqWordInDoc);
    }

    /**
     * get frequency of word in a document.
     * @param docId id of the document
     * @param word word in a document
     * @return frequency of the word
     */
    public int getFrequency(int docId , String word){
        return frequencyMap.getOrDefault(docId,Map.of()).getOrDefault(word,0);
    }
}