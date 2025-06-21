package com.practice.designpractice.QueueKafka.core;

import com.practice.designpractice.QueueKafka.consumer.Consumer;

import java.util.List;
import java.util.concurrent.*;


// Topic.java
// Represents a topic in the queue. Responsible for managing subscribers and delivering messages to them concurrently.
// Internally starts a dispatcher thread that listens for incoming messages on a blocking queue (LinkedBlockingQueue).
// When a message is published, it's added to the queue. The dispatcher takes it off the queue and sends it to each subscriber.
// Each message delivery to a subscriber is done on a new thread to ensure non-blocking and parallel delivery.

/**
 * Represents a Topic in the queue system.
 *
 * Responsibilities:
 * - Maintains a list of subscribers (Consumers) interested in the topic.
 * - Accepts messages from producers and queues them.
 * - Spawns a background dispatcher thread to continuously deliver messages to all subscribers.
 *
 * Threading:
 * - Uses a LinkedBlockingQueue to safely queue messages across threads.
 * - Dispatcher thread consumes messages from the queue and sends to all subscribers in **parallel** using new threads.
 *
 * Thread Safety:
 * - Subscriber list is thread-safe using CopyOnWriteArrayList.
 * - Message queue is thread-safe using LinkedBlockingQueue.
 */


// Introducing backpressure to slowdown write to consumers.

public class Topic {
    private final String name;

    // Thread-safe list to store all subscribers to this topic
    private final List<Consumer> subscribers = new CopyOnWriteArrayList<>();

    // Thread-safe queue to hold messages until they are dispatched
//    private final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
    // Bounded queue to enforce backpressure — max 100 messages
    private final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>(100);

    // Thread pool executor for concurrent message delivery to consumers
    private final ExecutorService deliveryExecutor;

    /**
     * Initializes the Topic with the given name and starts the dispatcher thread.
     * The dispatcher listens on the message queue and delivers messages to all subscribed consumers.
     */
    public Topic(String name) {
        this.name = name;

        // Start a background thread that handles message dispatching to consumers
        new Thread(this::dispatchMessages).start();


        // Thread pool with fixed size of 10 threads for consumer delivery
        // this.deliveryExecutor = Executors.newFixedThreadPool(10);
        // ThreadPool with fixed 10 threads, and bounded queue of 50 tasks
        this.deliveryExecutor = new ThreadPoolExecutor(
                5,                    // core threads
                10,                   // max threads
                60, TimeUnit.SECONDS,// idle timeout
                new LinkedBlockingQueue<>(50), // Bounded task queue
                new ThreadPoolExecutor.CallerRunsPolicy() // If full, run in caller's thread (throttles producer)
        );
    }

    /**
     * Subscribes a consumer to this topic.
     * The consumer will receive any future messages published to this topic.
     *
     * @param consumer the Consumer to subscribe
     */
    public void subscribe(Consumer consumer) {
        subscribers.add(consumer);
    }

    /**
     * Publishes a message to this topic.
     * This method is called by producers. It enqueues the message to the internal message queue.
     *
     * @param message the message to be delivered
     */
//    public void publish(Message message) {
//        messageQueue.offer(message); // Non-blocking enqueue
//    }
    // This method blocks if queue is full, adding natural backpressure on producers
    public void publish(Message message) {
        try {
            messageQueue.put(message); // blocks if queue is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Producer interrupted during publish", e);
        }
    }

    /**
     * Internal method that runs in a background thread.
     * This continuously takes messages off the message queue and delivers them to all subscribers.
     * Each delivery is executed on a separate thread to ensure parallel processing.
     */
    private void dispatchMessages() {
        while (true) {
            try {
                // Blocking call – waits if the queue is empty
                Message message = messageQueue.take();

                // Send message to each consumer in a separate thread (non-blocking)
                for (Consumer consumer : subscribers) {
                    // Submit delivery task to thread pool instead of starting a new thread
                    deliveryExecutor.submit(() -> consumer.consume(message));
//                    new Thread(() -> consumer.consume(message)).start();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupt flag
                break; // Exit the loop on interrupt
            }
        }
    }
}
