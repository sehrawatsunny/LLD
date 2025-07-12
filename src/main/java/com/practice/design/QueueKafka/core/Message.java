package com.practice.design.QueueKafka.core;


import lombok.Data;

// Represents a message to be sent by a producer and consumed by a consumer
@Data
public class Message {
    private final String id;
    private final String payload;
    private final String producerId;
    private final long timestamp;

    public Message(String id, String payload, String producerId) {
        this.id = id;
        this.payload = payload;
        this.producerId = producerId;
        this.timestamp = System.currentTimeMillis();
    }
}
