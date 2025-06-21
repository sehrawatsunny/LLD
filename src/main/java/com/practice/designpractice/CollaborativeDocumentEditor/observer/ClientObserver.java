package com.practice.designpractice.CollaborativeDocumentEditor.observer;

// ClientObserver.java
public interface ClientObserver {
    void notifyChange(String documentId, String updatedContent);
}
