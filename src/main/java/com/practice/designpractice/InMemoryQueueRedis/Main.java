package com.practice.designpractice.InMemoryQueueRedis;


import com.practice.designpractice.InMemoryQueueRedis.core.KeyValueStore;
import com.practice.designpractice.InMemoryQueueRedis.core.Pair;
import com.practice.designpractice.InMemoryQueueRedis.core.ValueObject;
import com.practice.designpractice.InMemoryQueueRedis.type.TypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://workat.tech/machine-coding/practice/design-key-value-store-6gz6cq124k65
/**
 * Main driver class for the In-Memory Key-Value Store.
 * Handles user input commands like put, get, delete, search, keys, and exit.
 * Ensures proper output formatting and exception handling.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KeyValueStore store = new KeyValueStore();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            // Stop execution on "exit"
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.split("\\s+");
            String command = parts[0];

            try {
                switch (command) {
                    case "put":
                        handlePut(store, parts);
                        break;

                    case "get":
                        handleGet(store, parts[1]);
                        break;

                    case "delete":
                        store.delete(parts[1]);
                        break;

                    case "search":
                        handleSearch(store, parts[1], parts[2]);
                        break;

                    case "keys":
                        handleKeys(store);
                        break;

                    default:
                        System.out.println("Unknown Command.");
                }
            } catch (TypeMismatchException e) {
                System.out.println("Data Type Error");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Handles the `put` command by building a list of attribute key-value pairs.
     */
    private static void handlePut(KeyValueStore store, String[] parts) {
        String key = parts[1];
        List<Pair<String, String>> attributes = new ArrayList<>();

        for (int i = 2; i < parts.length; i += 2) {
            String attrKey = parts[i];
            String attrVal = parts[i + 1];
            attributes.add(new Pair<>(attrKey, attrVal));
        }

        store.put(key, attributes);
    }

    /**
     * Handles the `get` command and prints the value object.
     */
    private static void handleGet(KeyValueStore store, String key) {
        ValueObject valueObject = store.get(key);
        if (valueObject == null) {
            System.out.println("No entry found for " + key);
        } else {
            System.out.println(valueObject); // uses overridden toString()
        }
    }

    /**
     * Handles the `search` command and prints the sorted list of matching keys.
     */
    private static void handleSearch(KeyValueStore store, String attrKey, String attrVal) {
        List<String> keys = store.search(attrKey, attrVal);
        String result = String.join(",", keys);
        System.out.println(result);
    }

    /**
     * Handles the `keys` command and prints all keys in sorted order.
     */
    private static void handleKeys(KeyValueStore store) {
        List<String> keys = store.keys();
        String result = String.join(",", keys);
        System.out.println(result);
    }
}