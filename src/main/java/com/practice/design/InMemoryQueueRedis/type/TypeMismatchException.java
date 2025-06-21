package com.practice.design.InMemoryQueueRedis.type;

public class TypeMismatchException extends RuntimeException {
    public TypeMismatchException(String message) {
        super(message);
    }
}
