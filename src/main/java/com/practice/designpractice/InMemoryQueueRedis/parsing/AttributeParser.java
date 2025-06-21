package com.practice.designpractice.InMemoryQueueRedis.parsing;


public class AttributeParser {

    /**
     * Parses a string input and converts it to the appropriate Java type.
     * Supported types: Boolean, Integer, Double, String
     *
     * @param value The raw string value from user input
     * @return The value converted to its appropriate Object type
     */

    public static Object parseValue(String value) {
        // Try parsing as Boolean
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        }

        // Try parsing as Integer
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // ignore the exception
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            // Ignore the exception
        }

        // Fallback to String , last option.
        return value;
    }
}

