package com.practice.designpractice.InMemorySqlDb.engine;

import com.practice.designpractice.InMemorySqlDb.core.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the storage engine.
 */
public class InMemoryStorageEngine implements IStorageEngine {
    private final Map<String, Table> tables = new HashMap<>();

    @Override
    public void addTable(String name, Table table) {
        tables.put(name, table);
    }

    @Override
    public Table getTable(String name) {
        return tables.get(name);
    }

    @Override
    public boolean containsTable(String name) {
        return tables.containsKey(name);
    }
}
