package com.practice.design.InMemorySearchEngine.processing;


import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TextProcessor handles normalization of input text.
 * It converts text to lowercase, splits by whitespace,
 * and removes common stop words to improve search quality.
 *
 * SOLID: Follows SRP - single responsibility of tokenizing and cleaning text.
 */
public class TextProcessor {
    private static final Set<String> STOP_WORDS = Set.of("is","a","on","at","to","and","in","of","are","which","the");

    public List<String> tokenize(String text){
        String[] words = text.toLowerCase().split("\\s+");
        // Need to remove all words , which are present in STOP_WORDS set.
        return Arrays.stream(words)
                .filter(word -> !STOP_WORDS.contains(word))
                .collect(Collectors.toList());
    }
}
