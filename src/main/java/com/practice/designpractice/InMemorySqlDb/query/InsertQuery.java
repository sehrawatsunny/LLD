package com.practice.designpractice.InMemorySqlDb.query;

import com.practice.designpractice.InMemorySqlDb.core.Row;
import com.practice.designpractice.InMemorySqlDb.core.Table;
import com.practice.designpractice.InMemorySqlDb.engine.DatabaseEngine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Command query to insert a row of values into a table.
 *
 * 📌 Responsibilities:
 * - Maps a list of values to column names in schema-defined order
 * - Delegates schema validation to the Table class
 * - Ensures insertion order of schema columns is respected
 *
 * ✅ SOLID Principles:
 * - SRP: Handles only the insert command logic
 * - OCP: Can support more complex insert validations later (nullable/defaults)
 * - Delegation: Schema validation is not handled here, but within Table
 */
public class InsertQuery implements IQuery {
    private final String tableName;
    private final List<Object> values;

    public InsertQuery(String tableName, List<Object> values) {
        this.tableName = tableName;
        this.values = values;
    }

    @Override
    public void execute(DatabaseEngine engine) {
        Table table = engine.getTable(tableName);
        if (table == null) {
            System.out.println("❌ Table not found: " + tableName);
            return;
        }

        // Verify the schema of the table.
        List<String> schemaCols = table.getSchema().getOrderedColumnNames();

        if (values.size() != schemaCols.size()) {
            System.out.println("❌ Column count mismatch.");
            return;
        }

        // Schema columns are stored in insertion order (via LinkedHashMap),
        // so we can safely map values from the input list in the same sequence.

        Map<String, Object> rowData = new LinkedHashMap<>();
//        Schema schema = table.getSchema();
        for (int i = 0; i < schemaCols.size(); i++) {
            String column = schemaCols.get(i);
            Object columnVal = values.get(i);

            // Expecting input in sequence of schema defined of columns.
            // schema column type -> value
//            if (!schema.isTypeValid(column, columnVal)) {
//                System.out.println("❌ Type mismatch: column=" + column + ", value=" + columnVal + ", type=" + columnVal.getClass().getSimpleName());
//                throw new IllegalArgumentException("Type Mismatch for column '" + column + "'");
//            }

            rowData.put(column, columnVal);
        }

        // Create a row object, which will hold data for all columns of a row.
        Row row = new Row(rowData);

        // table insert method will validate the schema of the complete row , and then insert it into the table
        table.insert(row);
        System.out.println("✅Row Inserted into table: " + tableName);
    }
}
