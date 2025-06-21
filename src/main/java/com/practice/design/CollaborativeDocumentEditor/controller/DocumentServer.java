package com.practice.design.CollaborativeDocumentEditor.controller;

// DocumentServer.java (Controller)

import com.practice.design.CollaborativeDocumentEditor.core.Document;
import com.practice.design.CollaborativeDocumentEditor.core.EditOperation;
import com.practice.design.CollaborativeDocumentEditor.observer.ClientObserver;

import java.util.*;

public class DocumentServer {
    private final Map<String, Document> documents = new HashMap<>();

    public void createDocument(String docId) {
        documents.put(docId, new Document(docId));
        System.out.println("✅ Document created: " + docId);
    }

    public void subscribe(String docId, ClientObserver client) {
        Document doc = documents.get(docId);

        if (doc != null) {
            doc.subscribe(client);
            System.out.println("📬 Client subscribed to " + docId);
        }
    }

    public String getCurrentContent(String docId) {
        Document doc = documents.get(docId);
        return doc != null ? doc.getContent() : null;
    }

    public void editDocument(String docId, EditOperation operation) {
        Document doc = documents.get(docId);
        if (doc != null) {
            doc.applyEdit(operation);
        }
    }

    public void printVersionHistory(String docId) {
        Document doc = documents.get(docId);
        if (doc != null) {
            List<String> docVersions = doc.getVersionHistory();
            for (int i = 0; i < docVersions.size(); i++) {
                System.out.println("Version " + i + ": " + docVersions.get(i));
            }
        }
    }
}