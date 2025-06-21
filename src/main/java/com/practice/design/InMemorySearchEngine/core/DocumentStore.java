package com.practice.design.InMemorySearchEngine.core;

import java.util.HashMap;
import java.util.Map;

/**
 * DocumentStore is responsible for storing the raw document text.
 * It assigns and manages unique IDs for each document added.
 *
 * SOLID: Follows SRP - dedicated to document storage.
 * It is decoupled from indexing and search logic to keep responsibilities isolated.
 */
public class DocumentStore {
    private final Map<Integer,String> documents = new HashMap<>();

    private int docIdCounter = 0 ;

    public int addDocument(String text){
        int docId = docIdCounter++;
        documents.put(docId,text);
        return docId;
    }

    public String getDocument(int docId){
        return documents.get(docId);
    }
}