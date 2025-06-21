package com.practice.design.CollaborativeDocumentEditor;

import com.practice.design.CollaborativeDocumentEditor.controller.DocumentServer;
import com.practice.design.CollaborativeDocumentEditor.observer.ClientObserver;
import com.practice.design.CollaborativeDocumentEditor.observer.ConcreteClient;
import com.practice.design.CollaborativeDocumentEditor.operations.InsertOperation;

public class DemoMain {
    public static void main(String[] args) {
        DocumentServer server = new DocumentServer();

        server.createDocument("doc-1");

        ClientObserver alice = new ConcreteClient("Alice");
        ClientObserver bob = new ConcreteClient("Bob");

        server.subscribe("doc-1", alice);
        server.subscribe("doc-1", bob);

        server.editDocument("doc-1", new InsertOperation("Alice", 0, " Hello "));
        server.editDocument("doc-1", new InsertOperation("Bob", 7, " World!"));

        server.printVersionHistory("doc-1");

    }
}
