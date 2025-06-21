package com.practice.designpractice.InMemorySqlDb.engine;

import com.practice.designpractice.InMemorySqlDb.query.IQuery;
import com.practice.designpractice.InMemorySqlDb.core.Table;

/**
 * Orchestrates query execution and delegates to storage engine.
 */
public class DatabaseEngine {
    private final IStorageEngine storageEngine;

    public DatabaseEngine(IStorageEngine iStorageEngine){
        this.storageEngine = iStorageEngine;
    }
    public void executeQuery(IQuery query){
        // Pass database engine to IQuery implementations.
        query.execute(this);
    }

    public void addTable(String name , Table table){
        storageEngine.addTable(name,table);
    }

    public Table getTable(String name){
        return storageEngine.getTable(name);
    }

    public boolean containsTable(String name){
        return storageEngine.containsTable(name);
    }
}