package com.practice.designpractice.InMemorySqlDb.core;

import java.util.Collections;
import java.util.Map;

/**
 * Represents a single row in a database table.
 *
 *  SRP: This class is responsible **only** for managing the column-value mapping of a single row.
 *  It is fully decoupled from table structure or schema validation — that responsibility lies with `Table` or `Schema`.
 *
 * Internally uses a Map to store values by column name.
 */
public class Row {

    // Holds column name -> value for this row
    private final Map<String, Object> rowValues;

    /**
     * Constructor for Row. Accepts a map of column-value pairs.
     *
     * @param values column -> value mapping for the row
     */
    public Row(Map<String, Object> values) {
        this.rowValues = values;
    }

    /**
     * Retrieves the value for a given column in the row.
     *
     * @param column column name
     * @return value for the column
     */
    public Object getColumnValue(String column) {
        return rowValues.get(column);
    }

    /**
     * Sets or updates the value for a given column.
     *
     * @param column column name
     * @param value new value to set
     */
    public void setColumnValue(String column, Object value) {
        rowValues.put(column, value);
    }

    /**
     * Returns all column-value pairs in the row.
     * The returned map is unmodifiable to prevent external tampering.
     *
     * @return unmodifiable map of all values
     */
    public Map<String, Object> getAllColumnValuesForARow() {
        return Collections.unmodifiableMap(rowValues);
    }
}
