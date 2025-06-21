package com.practice.design.InMemorySearchEngine;


import com.practice.design.InMemorySearchEngine.core.SearchEngine;
import com.practice.design.InMemorySearchEngine.ranking.FrequencyBasedRanking;

import java.util.List;

public class SearchEngineDemo {
    public static void main(String[] args) {

        // Create a search engine , and tell the ranking mechanism to be used.
        SearchEngine engine = new SearchEngine(new FrequencyBasedRanking());

        // Create a dataset.
        engine.createDataset("tech-blog");

        // store documents in the dataset.
        engine.addDocument("tech-blog", "apple is a fruit");
        engine.addDocument("tech-blog", "apple apple come on");
        engine.addDocument("tech-blog", "oranges are sour");
        engine.addDocument("tech-blog", "apple is sweet");
        engine.addDocument("tech-blog", "veggies are healthy");

        // Search for a term in the documents.
        List<String> results = engine.search("tech-blog", "apple");

        System.out.println("Search results for keyword 'apple': " + results.size());
        for (String doc : results) {
            System.out.println(doc);
        }

        // Since , we have ignored is , in the tokenization , no results in the output.
        results = engine.search("tech-blog", "is");
        System.out.println("Search results for keyword 'is': " + results.size());
        for (String doc : results) {
            System.out.println(doc);
        }

        results = engine.search("tech-blog", "sweet");
        System.out.println("Search results for keyword 'sweet': " + results.size());
        for (String doc : results) {
            System.out.println(doc);
        }
    }
}
