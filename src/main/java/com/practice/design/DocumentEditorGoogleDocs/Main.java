package com.practice.design.DocumentEditorGoogleDocs;

import com.practice.design.DocumentEditorGoogleDocs.exception.AccessDeniedException;
import com.practice.design.DocumentEditorGoogleDocs.model.AccessType;
import com.practice.design.DocumentEditorGoogleDocs.model.Document;
import com.practice.design.DocumentEditorGoogleDocs.model.DocumentVersion;
import com.practice.design.DocumentEditorGoogleDocs.model.User;
import com.practice.design.DocumentEditorGoogleDocs.service.DocumentService;

public class Main {
    public static void main(String[] args){
        // Create Users
        User alice = new User("U1","Alice");
        User bob = new User("U2", "Bob");
        User charlie = new User("U3", "Charlie");

        // Create the document service.

        DocumentService documentService = new DocumentService();

        // Alice creates a document.
        Document doc1 = documentService.createDocument(alice, "Hello, this is Alice's original content.");
        System.out.println("Document created by " + doc1.getOwner().getUserName() + ": " + doc1.getContent());

        // Alice shares content with bob with read access.
        documentService.shareDocument(alice,doc1.getId(),bob, AccessType.READ);

        //Bob Tries to read it.
        System.out.println("Bob reads: " + documentService.readDocument(bob, doc1.getId()));

        // Bob tries to edit it.
        try {
            documentService.editDocument(bob,doc1.getId(),"Bob's edit");
        } catch (AccessDeniedException ex){
            System.out.println("Edit denied: " + ex.getMessage());
        }

       // Alice gives write access to Charlie
        documentService.shareDocument(alice,doc1.getId(),charlie,AccessType.WRITE);

        // Charlies Tries to read it.
        System.out.println("Charlie reads: " + documentService.readDocument(charlie, doc1.getId()));

        // Charlie edits it
        documentService.editDocument(charlie, doc1.getId(), "Charlie's updated version");

        //Charlies Tries to re-read after edit.
        System.out.println("Charlie reads after edit: " + documentService.readDocument(charlie, doc1.getId()));

        // Print version history
        System.out.println("\nVersion history:");
        for (DocumentVersion version : documentService.getHistory(doc1.getId())) {
            System.out.println(version.getTimestamp() + " - Edited by: " + version.getEditedBy().getUserName());
            System.out.println("Content: " + version.getContent());
            System.out.println("---");
        }
    }
}
