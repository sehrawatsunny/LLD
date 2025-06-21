package com.practice.designpractice.InMemorySqlDb.query;

import com.practice.designpractice.InMemorySqlDb.engine.DatabaseEngine;

/**
 * Represents an abstract query.
 * This is the base of the Command Pattern where each concrete query implements execute().
 */
public interface IQuery {
    void execute(DatabaseEngine engine);
}

