package com.practice.design.InMemorySqlDb.query;
import com.practice.design.InMemorySqlDb.core.Row;
import com.practice.design.InMemorySqlDb.core.Table;
import com.practice.design.InMemorySqlDb.engine.DatabaseEngine;

import java.util.Map;
/**
 * Command to update rows matching a condition.
 */

//UPDATE users SET name = 'Robert' WHERE id = 2;
//UpdateQuery updateQuery = new UpdateQuery(
//        "users",                    // tableName
//        "id",                       // whereColumn
//        2,                          // whereValue
//        Map.of("name", "Robert")    // updatedValues
//);


public class UpdateQuery implements IQuery {
    private final String tableName;
    private final String whereColumn;
    private final Object whereValue;
    private final Map<String,Object> updatedValues;

    public UpdateQuery(String tableName , String whereColumn , Object whereValue , Map<String,Object> updatedValues){
        this.tableName = tableName;
        this.whereColumn = whereColumn;
        this.whereValue = whereValue;
        this.updatedValues = updatedValues;
    }

    // Updating the complete row here , for a provided id.
    // Row is provided as a map of column name  & column values
    @Override
    public void execute(DatabaseEngine engine) {
        Table table = engine.getTable(tableName);
        if (table == null) {
            System.out.println("Table '" + tableName + "' does not exist.");
            return;
        }

        for(Row row : table.getRows()){
            if(whereValue.equals(row.getColumnValue(whereColumn))){
                for(Map.Entry<String,Object> entry : updatedValues.entrySet()){
                    if(table.getSchema().isTypeValid(entry.getKey(), entry.getValue())){
                        System.out.println("Type mismatch for column '" + entry.getKey() + "'");
                        continue;
                    }
                    row.setColumnValue(entry.getKey(),entry.getValue());
                }
            }
        }
        System.out.println("Rows updated in '" + tableName + "'.");
    }
}
