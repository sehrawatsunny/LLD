package com.practice.design.QueueKafka;

import com.practice.design.QueueKafka.broker.Broker;
import com.practice.design.QueueKafka.broker.Producer;
import com.practice.design.QueueKafka.consumer.Consumer;
import com.practice.design.QueueKafka.core.Message;
import com.practice.design.QueueKafka.core.Topic;

// Main.java
// Entry point of the application to demonstrate a Kafka-like in-memory distributed queue system
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Step 1: Create the central broker which manages all topics
        Broker broker = new Broker();

        // Step 2: Create two topics under the broker
        Topic topic1 = broker.createTopic("topic1");
        Topic topic2 = broker.createTopic("topic2");

        // Step 3: Create two producers that will publish messages to topics
        Producer producer1 = new Producer("producer1", broker);
        Producer producer2 = new Producer("producer2", broker);

        // Step 4: Create five consumers
        Consumer consumer1 = new Consumer("consumer1");
        Consumer consumer2 = new Consumer("consumer2");
        Consumer consumer3 = new Consumer("consumer3");
        Consumer consumer4 = new Consumer("consumer4");
        Consumer consumer5 = new Consumer("consumer5");

        // Step 5: Subscribe all 5 consumers to topic1
        // Every message published to topic1 will be delivered to all these consumers
        topic1.subscribe(consumer1);
        topic1.subscribe(consumer2);
        topic1.subscribe(consumer3);
        topic1.subscribe(consumer4);
        topic1.subscribe(consumer5);

        // Step 6: Subscribe only consumer1, consumer3, and consumer4 to topic2
        // Only these three consumers will receive messages published to topic2
        topic2.subscribe(consumer1);
        topic2.subscribe(consumer3);
        topic2.subscribe(consumer4);

        // Step 7: Publish 3 messages to topic1 using both producers
        producer1.publish("topic1", new Message("1", "Message 1", "producer1"));
        producer1.publish("topic1", new Message("2", "Message 2", "producer1"));
        producer2.publish("topic1", new Message("3", "Message 3", "producer2"));

        // Step 8: Publish 2 messages to topic2
        producer1.publish("topic2", new Message("4", "Message 4", "producer1"));
        producer2.publish("topic2", new Message("5", "Message 5", "producer2"));

        // Step 9: Give background threads time to deliver all messages
        Thread.sleep(3000);
    }
}

