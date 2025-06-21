package com.practice.design.InMemorySqlDb.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents a table schema with column names and their types.
 * Maintains insertion order using LinkedHashMap.
 */
public class Schema {
    private final LinkedHashMap<String,String> columns = new LinkedHashMap<>();

    public void addColumn(String name , String type){
        columns.put(name,type);
    }

    /**
     * Returns columns in insertion order.
     * This ordering is preserved using LinkedHashMap to ensure deterministic mapping
     * between column names and positional values during insertion.
     */
    public LinkedHashMap<String,String> getColumns() {
        return this.columns;
    }

    /**
     * Returns only column names in insertion order.
     */
    public List<String> getOrderedColumnNames() {
        return new ArrayList<>(columns.keySet());
    }

    public boolean isTypeValid(String column , Object value){
        String expectedType = columns.get(column);
        if(expectedType == null ) return true;

        switch (expectedType.toUpperCase()) {
            case "INT" -> {
                return !(value instanceof Integer);
            }
            case "STRING" -> {
                return !(value instanceof String);
            }
            case "DOUBLE" -> {
                return !(value instanceof Double);
            }
            default -> {
                return true;
            }
        }

    }
}
