package com.practice.design.InMemoryQueueRedis.core;

import com.practice.design.InMemoryQueueRedis.parsing.AttributeParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeyValueStore {

    //Thread safe in-value keyStore.
    private final Map<String, ValueObject> store = new ConcurrentHashMap<>();

    /**
     * Adds or updates a key-value entry with parsed attributes.
     * If the key exists, the old value is replaced.
     */
    public void put(String key, List<Pair<String, String>> attributes) {
        ValueObject valueObject = new ValueObject();

        for (Pair<String, String> pair : attributes) {
            Object parsedValue = AttributeParser.parseValue(pair.getValue());
            valueObject.putAttribute(pair.getKey(), parsedValue);
        }

        store.put(key, valueObject);
    }

    /**
     * Returns the value associated with a key.
     * Returns null if the key doesn't exist.
     */
    public ValueObject get(String key) {
        return store.get(key);
    }

    /**
     * Removes the key and value from its store.
     */
    public void delete(String key) {
        store.remove(key);
    }

    /**
     * Return a sorted list of all keys in the store.
     */
    public List<String> keys() {
        List<String> result = new ArrayList<>(store.keySet());
        Collections.sort(result);
        return result;
    }

    /**
     * Searches the store for keys where the specified attribute matches the given value.
     *
     * @param attributeKey   Attribute name to search for
     * @param attributeValue String value to match against
     * @return List of matching keys in sorted order
     */
    public List<String> search(String attributeKey, String attributeValue) {
        List<String> matchedKeys = new ArrayList<>();

        // Parse the search value to the correct Java type (String, Integer, Boolean, Double)
        Object parsedValue = AttributeParser.parseValue(attributeValue);

        // Loop through all key-value entries in the store
        for (String key : store.keySet()) {
            ValueObject valueObj = store.get(key);

            // Get the value of the specified attribute in this ValueObject
            Object attributeValuePresentAtAttributeKey = valueObj.getAttribute(attributeKey);

            // Match both key presence and equality of value
            if (attributeValuePresentAtAttributeKey != null &&
                    attributeValuePresentAtAttributeKey.equals(parsedValue)) {
                matchedKeys.add(key);
            }
        }

        // Requirement: return matching keys in lexicographically sorted order
        Collections.sort(matchedKeys);
        return matchedKeys;
    }
}

