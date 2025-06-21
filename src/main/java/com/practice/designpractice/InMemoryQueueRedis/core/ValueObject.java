package com.practice.designpractice.InMemoryQueueRedis.core;

import com.practice.designpractice.InMemoryQueueRedis.type.TypeRegistry;

import java.util.*;


//Represents the value associated with a key in the key-value store.
//Each value consists of multiple attributes (key-value pairs) where:
//Keys are strings (attribute names)
//Values are typed (String, Integer, Double, Boolean)

//This object enforces type consistency via TypeRegistry.

public class ValueObject {
    // Stores attribute key-value pairs in the order they were inserted.
    // We use LinkedHashMap to preserve insertion order for clean & predictable output in toString().
    private final Map<String, Object> attributes = new LinkedHashMap<>();

    // Singleton instance that tracks global attribute type constraints across all ValueObjects.
    private final TypeRegistry registry = TypeRegistry.getInstance();

    // Adds or updates an attribute in this value object after type validation.
    public void putAttribute(String key, Object value) {
        registry.register(key, value); // Validate type globally
        attributes.put(key, value);    // Save locally
    }

    // Returns the value for a specific attribute key.
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    // Returns an unmodifiable (read-only) copy of all attributes.
    public Map<String, Object> getAllAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public String toString() {
        List<String> result = new ArrayList<>();
        for (String key : attributes.keySet()) {
            String res = key + ": " + attributes.get(key); // Add space after colon for formatting
            result.add(res);
        }
        return String.join(", ", result);
    }
}
