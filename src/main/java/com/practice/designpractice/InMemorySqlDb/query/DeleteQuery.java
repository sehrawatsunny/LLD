package com.practice.designpractice.InMemorySqlDb.query;

import com.practice.designpractice.InMemorySqlDb.core.Row;
import com.practice.designpractice.InMemorySqlDb.core.Table;
import com.practice.designpractice.InMemorySqlDb.engine.DatabaseEngine;

import java.util.Iterator;

//DELETE FROM users WHERE id = 1;
//DeleteQuery deleteQuery = new DeleteQuery(
//    "users",     // tableName
//    "id",        // whereColumn
//    1            // whereValue
//);

/**
 * Command to delete rows matching a condition.
 */
public class DeleteQuery implements IQuery {
    private final String tableName;
    private final String whereColumn;
    private final Object whereValue;

    public DeleteQuery(String tableName, String whereColumn, Object whereValue) {
        this.tableName = tableName;
        this.whereColumn = whereColumn;
        this.whereValue = whereValue;
    }

    @Override
    public void execute(DatabaseEngine engine) {
        Table table = engine.getTable(tableName);
        if (table == null) {
            System.out.println("Table '" + tableName + "' does not exist.");
            return;
        }

        Iterator<Row> iterator = table.getRows().iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();

            if (whereValue.equals(row.getColumnValue(whereColumn))) {
                iterator.remove();
            }
        }
        System.out.println("Rows deleted from '" + tableName + "'.");
    }
}
