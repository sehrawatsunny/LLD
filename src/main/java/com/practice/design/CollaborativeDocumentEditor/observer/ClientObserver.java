package com.practice.design.CollaborativeDocumentEditor.observer;

// ClientObserver.java
public interface ClientObserver {
    void notifyChange(String documentId, String updatedContent);
}
