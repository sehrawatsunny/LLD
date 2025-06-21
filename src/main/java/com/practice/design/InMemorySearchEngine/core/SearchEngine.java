package com.practice.design.InMemorySearchEngine.core;

import com.practice.design.InMemorySearchEngine.dataset.DataSet;
import com.practice.design.InMemorySearchEngine.ranking.IRankingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Public-facing API for managing datasets and performing search.
 * The Search engine will store the address of dataset
 * so that it knows the in-memory location of every dataset.
 * SOLID: Follows Dependency Inversion by depending on IRankingStrategy abstraction.
 */
public class SearchEngine {

    // Storing location of a dataset object where dataset is located in-memory
    // corresponding to name told by user.
    private final Map<String, DataSet> datasets = new HashMap<>();

    private final IRankingStrategy rankingStrategy;

    //User needs to provide the ranking strategy for each dataset being created.
    public SearchEngine(IRankingStrategy rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
    }

    private DataSet getDataset(String name) {
        if (!datasets.containsKey(name)) {
            throw new IllegalArgumentException("Dataset not found: " + name);
        }
        return datasets.get(name);
    }

    /**
     * Create a new dataset in-memory and search engine will store the address of dataset.
     *
     * @param name name of dataset to uniquely identify the in-memory location.
     */
    public void createDataset(String name) {
        if (datasets.containsKey(name)) {
            throw new IllegalArgumentException("Dataset already exists:" + name);
        }
        datasets.put(name, new DataSet(rankingStrategy));
    }

    /**
     * @param datasetName which dataset the document needs to be created in-memory.
     * @param text        create a new document in a dataset with the given text.
     */
    public void addDocument(String datasetName, String text) {
        DataSet dataSet = getDataset(datasetName);
        dataSet.addDocument(text);
    }

    /**
     * @param datasetName , where the term needs to be searched
     * @param term        , which term needs to find in dataset?
     * @return list of document id's in string ordered on the basis of high frequency of term.
     */
    public List<String> search(String datasetName, String term) {
        DataSet dataSet = getDataset(datasetName);
        return dataSet.search(term);
    }
}
