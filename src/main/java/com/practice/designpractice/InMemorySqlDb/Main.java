package com.practice.designpractice.InMemorySqlDb;

import com.practice.designpractice.InMemorySqlDb.engine.DatabaseEngine;
import com.practice.designpractice.InMemorySqlDb.engine.IStorageEngine;
import com.practice.designpractice.InMemorySqlDb.engine.InMemoryStorageEngine;
import com.practice.designpractice.InMemorySqlDb.query.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        IStorageEngine storage = new InMemoryStorageEngine();
        DatabaseEngine engine = new DatabaseEngine(storage);

        Map<String, String> schema = new LinkedHashMap<>();
        schema.put("id", "INT");
        schema.put("name", "STRING");
        CreateTableQuery createTableQuery = new CreateTableQuery("users", schema);

        engine.executeQuery(createTableQuery);

        InsertQuery insertQuery = new InsertQuery("users", List.of(1, "Alice"));
        engine.executeQuery(insertQuery);

        engine.executeQuery(new SelectAllQuery("users"));

        engine.executeQuery(new UpdateQuery("users", "id", 2, Map.of("name", "Robert")));

        engine.executeQuery(new SelectAllQuery("users"));

        engine.executeQuery(new DeleteQuery("users", "id", 1));

        engine.executeQuery(new SelectAllQuery("users"));


        Map<String, String> schema2 = new LinkedHashMap<>();
        schema2.put("id", "INT");
        schema2.put("name", "STRING");
        schema2.put("country","STRING");

        CreateTableQuery createTableQuery2 = new CreateTableQuery("newUsers", schema2);
        engine.executeQuery(createTableQuery2);

        InsertQuery insertQuery2 = new InsertQuery("newUsers", List.of(1, "Alice","INDIA"));
        engine.executeQuery(insertQuery2);

        engine.executeQuery(new SelectAllQuery("newUsers"));

        engine.executeQuery(new UpdateQuery("newUsers", "id", 2, Map.of("name", "Robert","country","USA")));

        engine.executeQuery(new SelectAllQuery("newUsers"));

        engine.executeQuery(new DeleteQuery("newUsers", "id", 1));

        engine.executeQuery(new SelectAllQuery("newUsers"));
    }

}
