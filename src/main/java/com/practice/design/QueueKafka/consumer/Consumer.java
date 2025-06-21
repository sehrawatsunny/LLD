package com.practice.design.QueueKafka.consumer;


//Consumer class focused on its core responsibility — consuming messages —
// instead of being directly tied to how it's executed (i.e., threading behavior).
// This adheres to the Single Responsibility Principle (SRP) from SOLID.

import com.practice.design.QueueKafka.core.Message;

// Represents a consumer that consumes messages from subscribed topics
public class Consumer{

    private final String consumerId;

    public Consumer(String id){
        this.consumerId = id;
    }

    public String getConsumerId() {
        return consumerId;
    }

    // Revieve messages
    public void consume(Message message){
        System.out.println(consumerId + " received " + message.getPayload());
    }
}
