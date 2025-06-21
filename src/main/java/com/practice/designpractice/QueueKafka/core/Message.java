package com.practice.designpractice.QueueKafka.core;


// Represents a message to be sent by a producer and consumed by a consumer
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

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public String getProducerId() {
        return producerId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
