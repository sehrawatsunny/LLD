package com.practice.designpractice.InMemorySearchEngine.ranking;

import com.practice.designpractice.InMemorySearchEngine.indexing.WordFrequencyStore;

import java.util.List;
import java.util.Set;

/**
 * Defines contract for ranking documents.
 *
 * SOLID: Interface Segregation & Dependency Inversion principles.
 */
/**
 * Interface for implementing different ranking strategies for search results.
 *
 * Each implementation should rank a set of document IDs based on a given search term
 * and the word frequency information provided.
 *
 * This supports the Open/Closed Principle — new ranking strategies can be introduced
 * without changing existing code, and clients depend on abstraction, not implementation.
 *
 * Returns:
 * - A list of document IDs ranked in descending order of relevance to the term
 */
public interface IRankingStrategy {

    // Method Parameters:
    // docIds: Set of document IDs that matched the search term
    // term: The search term queried by the user
    // frequencyStore: Store containing word frequencies used to calculate ranking
    List<Integer> rank(Set<Integer> docsId , String term , WordFrequencyStore frequencyStore);
}