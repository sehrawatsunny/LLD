package com.practice.design.aRedisImportant.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe O(1) LRU cache implementation.
 * Methods: put, get, remove — all synchronized and constant time.
 * This version works with String keys and String values.
 */
public class LruCache {

    private static class Node {
        Node prev, next;
        String key;
        String value;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<String, Node> map;
    private final Node head;
    private final Node tail;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public synchronized String get(String key) {
        Node node = map.get(key);
        if (node == null)
            return null;
        removeNode(node);
        insertFront(node);
        return node.value;
    }

    public synchronized void put(String key, String value) {
        if (map.containsKey(key)) {
            removeNode(map.get(key));
        } else if (map.size() == capacity) {
            removeNode(tail.prev);
        }
        insertFront(new Node(key, value));
    }

    public synchronized void remove(String key) {
        Node node = map.get(key);
        if (node != null) {
            removeNode(node);
        }
    }

    private void removeNode(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertFront(Node node) {
        map.put(node.key, node);
        Node next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    public synchronized void printCache() {
        System.out.println("Cache state (MRU ➝ LRU):");
        Node current = head.next;
        while (current != tail) {
            System.out.print("(" + current.key + " -> " + current.value + ") ");
            current = current.next;
        }
        System.out.println("\n");
    }
}
