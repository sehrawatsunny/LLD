package com.practice.design.InMemorySqlDb.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a table in the in-memory SQL database.
 *
 * 📌 Responsibilities:
 * - Holds the structure of the table via a {@link Schema}.
 * - Stores actual data as a list of {@link Row} objects.
 * - Validates column types against schema during insertion.
 *
 * 🔁 Interacts with:
 * - {@link Schema}: to validate column types.
 * - {@link Row}: to store and retrieve row-level data.
 *
 * ✅ SRP: This class deals only with the **data and schema management** of a single table.
 */
public class Table {

    // Schema defining column names and data types
    private final Schema schema;

    // List of all rows stored in the table
    private final List<Row> rows = new ArrayList<>();

    /**
     * Creates a new table with the specified schema.
     *
     * @param schema the schema definition for this table
     */
    public Table(Schema schema) {
        this.schema = schema;
    }

    /**
     * Inserts a new row into the table after validating against the schema.
     *
     * @param row the row to be inserted
     * @throws IllegalArgumentException if any column has a type mismatch
     */
    public void insert(Row row){
        // Validate the schema of all the columns in a row.
        Map<String,Object> map = row.getAllColumnValuesForARow();
        for(String key : map.keySet()){
            if(schema.isTypeValid(key, map.get(key))){
                throw new IllegalArgumentException("Type Mismatch for column '" + key + "'");
            }
        }
        // If schema validated, add it to the table.
        rows.add(row);
    }

    /**
     * Retrieves all rows stored in the table.
     *
     * @return list of rows
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Returns the schema associated with this table.
     *
     * @return the table schema
     */
    public Schema getSchema() {
        return schema;
    }
}
