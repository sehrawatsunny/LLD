package com.practice.designpractice.InMemorySqlDb.query;

import com.practice.designpractice.InMemorySqlDb.core.Schema;
import com.practice.designpractice.InMemorySqlDb.core.Table;
import com.practice.designpractice.InMemorySqlDb.engine.DatabaseEngine;

import java.util.Map;

/**
 * Query to create a new table with a specified schema.
 */
public class CreateTableQuery implements IQuery {

    private final String tableName;
    private final Map<String, String> schemaDefinition;


    public CreateTableQuery(String tableName, Map<String, String> schemaDefinition) {
        this.tableName = tableName;
        this.schemaDefinition = schemaDefinition;
    }

    @Override
    public void execute(DatabaseEngine engine) {
        if (engine.containsTable(tableName)) {
            System.out.println("Table already exists: " + tableName);
            return;
        }

        // Created a schema
        Schema schema = new Schema();
        for (Map.Entry<String, String> entry : schemaDefinition.entrySet()) {
            schema.addColumn(entry.getKey(), entry.getValue());
        }

        //Create a new table object with the schema provided.
        Table table = new Table(schema);

        // Add the table to the mysqlEngine.
        engine.addTable(tableName, table);

        System.out.println("✅ Table created: " + tableName);
    }
}