package com.practice.designpractice.InMemorySearchEngine.dataset;

import com.practice.designpractice.InMemorySearchEngine.processing.TextProcessor;
import com.practice.designpractice.InMemorySearchEngine.core.DocumentStore;
import com.practice.designpractice.InMemorySearchEngine.indexing.InvertedIndex;
import com.practice.designpractice.InMemorySearchEngine.indexing.WordFrequencyStore;
import com.practice.designpractice.InMemorySearchEngine.ranking.IRankingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a dataset containing multiple documents.
 * Coordinates text processing, indexing, and ranking.
 *
 * SOLID: Follows SRP by orchestrating components while delegating responsibilities.
 */
public class DataSet {
    // Stores document and generate id of document.
    private final DocumentStore documentStore = new DocumentStore();
    // Stores word -> Set of documentId's mapping, where the word is present.
    private final InvertedIndex invertedIndex = new InvertedIndex();
    // Store for a map(docId -> map(word , freq)) for a document
    private final WordFrequencyStore wordFrequencyStore = new WordFrequencyStore();
    // Generate searchable tokens for a text.
    private final TextProcessor textProcessor = new TextProcessor();

    // Generates ranking of documents based on the frequency of search term in each document.
    private IRankingStrategy rankingStrategy;

    // Provide ranking strategy in the search engine, while creating a dataset.
    public DataSet(IRankingStrategy rankingStrategy){
        this.rankingStrategy = rankingStrategy;
    }

    //Adding document to a dataset.
    public void addDocument(String text){
        int docId = documentStore.addDocument(text);

        // Tokenize all the words of the document.
        List<String> tokens = textProcessor.tokenize(text);

        // Add all the token to the invertedIndex map , which will store the docId .
        for(String token : tokens){
            invertedIndex.add(token,docId);
        }

        // store the frequency of all the words in the frequency store.
        wordFrequencyStore.addFrequencies(docId,tokens);
    }

    // Search for term in all the documents in a dataset.
    // Return the document text by adding them all to an arrayList.
    public List<String> search(String term){
        // Since, we have tokenized the words, by converting to lowercase.
        term = term.toLowerCase();

        // We know through invertedIndex , which all documents contain the term.
        Set<Integer> matchingDocIds = invertedIndex.getMatchingDocuments(term);

        // Return the docId based on frequency of word in all documents.
        List<Integer> rankedDocIds = rankingStrategy.rank(matchingDocIds,term,wordFrequencyStore);

        List<String> results = new ArrayList<>();
        for (int docId : rankedDocIds) {
            // Fetch the content of document from document store, by passing the sorted document id.
            results.add(documentStore.getDocument(docId));
        }
        return results;
    }
}
