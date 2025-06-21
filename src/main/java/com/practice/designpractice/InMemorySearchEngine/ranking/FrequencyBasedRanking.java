package com.practice.designpractice.InMemorySearchEngine.ranking;

import com.practice.designpractice.InMemorySearchEngine.indexing.WordFrequencyStore;

import java.util.*;
/**
 * Ranks documents based on the frequency of the term.
 *
 * SOLID: Follows OCP - can be extended with other strategies without modifying this.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Ranks documents based on the frequency of the term using a helper class for sorting.
 * <p>
 * SOLID: Follows OCP - easily extendable with new strategies; SRP by delegating ranking logic here.
 */
public class FrequencyBasedRanking implements IRankingStrategy {
    @Override
    public List<Integer> rank(Set<Integer> docIds, String term, WordFrequencyStore wordFrequencyStore) {
        List<SortTerms> sortTermsList = new ArrayList<>();
        for (int docId : docIds) {
            int freq = wordFrequencyStore.getFrequency(docId, term);
            sortTermsList.add(new SortTerms(docId, freq));
        }

//        // Sort using lambda
//        sortTermsList.sort((a, b) -> Integer.compare(b.freqOfTerm, a.freqOfTerm));

        // Sort documents in descending order of frequency
        Collections.sort(sortTermsList, (a, b) -> b.freqOfTerm - a.freqOfTerm);

        // Collect document IDs
        List<Integer> rankedDocIds = new ArrayList<>();
        for (SortTerms sortTerm : sortTermsList) {
            rankedDocIds.add(sortTerm.docId);
        }

        return rankedDocIds;
    }
}

/**
 * Helper class for storing document ID and frequency of the term for sorting.
 */
class SortTerms {
    int docId;
    int freqOfTerm;

    public SortTerms(int docId, int freqOfTerm) {
        this.docId = docId;
        this.freqOfTerm = freqOfTerm;
    }
}