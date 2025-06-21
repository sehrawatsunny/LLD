package com.practice.designpractice.InMemorySqlDb.engine;

// ===== IStorageEngine.java =====


import com.practice.designpractice.InMemorySqlDb.core.Table;

/**
 * Interface to abstract how tables are stored (in-memory, file-based, etc.).
 */
public interface IStorageEngine {
    void addTable(String name , Table table);
    Table getTable(String name);
    boolean containsTable(String name);
}
