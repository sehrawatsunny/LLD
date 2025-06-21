package com.practice.design.InMemorySqlDb.query;

import com.practice.design.InMemorySqlDb.core.Table;
import com.practice.design.InMemorySqlDb.engine.DatabaseEngine;

/**
 * Query to count the number of rows in a table.
 *
 * ✅ SRP: Focused only on row count.
 * 🔁 Interacts with: DatabaseEngine → Table → List<Row>
 */
public class CountQuery implements IQuery {

    private final String tableName;

    public CountQuery(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute(DatabaseEngine engine) {
        Table table = engine.getTable(tableName);

        if (table == null) {
            System.out.println("❌ Table not found: " + tableName);
            return;
        }

        int rowCount = table.getRows().size();
        System.out.println("🔢 Row count in '" + tableName + "': " + rowCount);
    }
}