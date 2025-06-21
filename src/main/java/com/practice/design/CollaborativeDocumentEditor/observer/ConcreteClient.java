package com.practice.design.CollaborativeDocumentEditor.observer;

// ConcreteClient.java
public class ConcreteClient implements ClientObserver {
    private final String clientId;

    public ConcreteClient(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void notifyChange(String documentId, String updatedContent) {
        System.out.println("🔔 [" + clientId + "] Document [" + documentId + "] Updated: " + updatedContent);
    }
}
