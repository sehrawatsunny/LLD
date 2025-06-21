package com.practice.design.QueueKafka.broker;


// Represents a producer that publishes messages to one or more topics

import com.practice.design.QueueKafka.core.Message;
import com.practice.design.QueueKafka.core.Topic;

public class Producer {
    private final String producerId;
    private final Broker broker;

    public Producer(String id , Broker broker){
        this.producerId = id;
        this.broker = broker;
    }

    public void publish(String topicName , Message message){
        Topic topic = broker.getTopic(topicName);
        if(topic!=null){
            topic.publish(message);
        }
    }
}