package com.practice.design.aRedisImportant;

import com.practice.design.aRedisImportant.service.RedisLikeStore;

public class Main {
    public static void main(String[] args) {
        RedisLikeStore store = new RedisLikeStore(3);

        store.put("u1", "Sunny Sehrawat");
        store.put("u2", "Roshni Kapoor");
        store.put("u3", "Aryan Dev");
        store.printCache();

        System.out.println("Get u1: " + store.get("u1")); // promotes u1
        store.printCache();

        store.put("u4", "Anaya Rathi"); // evicts LRU (u2)
        store.printCache();

        store.remove("u3"); // remove u3
        store.put("u5", "Raj Malhotra"); // another put
        store.printCache();

        System.out.println("Search 'sunny': " + store.search("sunny"));
        System.out.println("Search 'rathi': " + store.search("rathi"));
        System.out.println("Search 'dev': " + store.search("dev")); // u3 was removed , expect empty result

        store.printCache();
    }
}