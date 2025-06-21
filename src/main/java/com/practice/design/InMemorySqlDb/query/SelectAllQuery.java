package com.practice.design.InMemorySqlDb.query;

import com.practice.design.InMemorySqlDb.core.Row;
import com.practice.design.InMemorySqlDb.core.Table;
import com.practice.design.InMemorySqlDb.engine.DatabaseEngine;

/**
 * Query to print all rows in a table.
 */
public class SelectAllQuery implements IQuery {

    private final String tableName;

    public SelectAllQuery(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute(DatabaseEngine engine) {
        Table table = engine.getTable(tableName);
        if (table == null) {
            System.out.println("❌ Table not found: " + tableName);
            return;
        }

        System.out.println("📋 Data from table: " + tableName);
        for (Row row : table.getRows()) {
            System.out.println(row.getAllColumnValuesForARow());
        }
    }
}
