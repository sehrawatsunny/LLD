package com.practice.design.InMemorySqlDb.engine;

// ===== IStorageEngine.java =====


import com.practice.design.InMemorySqlDb.core.Table;

/**
 * Interface to abstract how tables are stored (in-memory, file-based, etc.).
 */
public interface IStorageEngine {
    void addTable(String name , Table table);
    Table getTable(String name);
    boolean containsTable(String name);
}
