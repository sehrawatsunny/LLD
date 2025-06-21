package com.practice.design.QueueKafka.broker;

import com.practice.design.QueueKafka.core.Topic;

import java.util.concurrent.ConcurrentHashMap;

// Acts as a central manager that maintains and provides access to topics
// - Responsible for creation and retrieval of Topics
// - Ensures only one instance of each topic name exists (singleton per topic)
// - Encourages separation of concern: Producers and Consumers interact with Broker to fetch topics, not manage them directly
// - Implements thread-safe storage using ConcurrentHashMap

public class Broker {
    // Map to store topic name -> Topic instance mapping
    // Using ConcurrentHashMap for thread-safe access since multiple producers/consumers may interact concurrently
    private final ConcurrentHashMap<String, Topic> topics = new ConcurrentHashMap<>();

    /**
     * Creates a new topic with the given name.
     * If a topic with the same name already exists, it will be overwritten.
     *
     * @param name Unique name of the topic.
     * @return The created Topic instance.
     */
    public Topic createTopic(String name) {
        Topic topic = new Topic(name);     // Instantiates a new Topic
        topics.put(name, topic);           // Adds it to the internal map
        return topic;
    }

    /**
     * Retrieves a topic by name.
     *
     * @param name Name of the topic.
     * @return Topic instance if it exists; otherwise, null.
     */
    public Topic getTopic(String name) {
        return topics.get(name);           // Fetches topic from map
    }
}
