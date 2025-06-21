package com.practice.design.InMemorySqlDb.query;

import com.practice.design.InMemorySqlDb.engine.DatabaseEngine;

/**
 * Represents an abstract query.
 * This is the base of the Command Pattern where each concrete query implements execute().
 */
public interface IQuery {
    void execute(DatabaseEngine engine);
}

