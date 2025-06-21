package com.practice.design.DocumentEditorGoogleDocs.service;


import com.practice.design.DocumentEditorGoogleDocs.model.AccessType;
import com.practice.design.DocumentEditorGoogleDocs.model.Document;
import com.practice.design.DocumentEditorGoogleDocs.model.DocumentVersion;
import com.practice.design.DocumentEditorGoogleDocs.model.User;
import com.practice.design.DocumentEditorGoogleDocs.exception.AccessDeniedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentService {
//    Key   = document ID (like "DOC1", "DOC2")
//    Value = the corresponding Document object
    private final Map<String, Document> documents = new HashMap<>();
    private int docCounter = 1;

    public Document createDocument(User owner, String content) {
        String id = "DOC" + docCounter++;
        Document doc = new Document(id, owner, content);
        documents.put(id, doc);
        return doc;
    }

    private Document getDocument(String docId) {
        if (!documents.containsKey(docId)) {
            throw new IllegalArgumentException("Document not found.");
        }
        return documents.get(docId);
    }

    public void shareDocument(User sharedBy, String docId, User sharedTo, AccessType accessType) {
        // if the user which is sharing the document is the owner of the document.
        Document doc = getDocument(docId);
        if (!doc.getOwner().getUserId().equals(sharedBy.getUserId())) {
            throw new AccessDeniedException("Only owner can share the document.");
        }
        doc.addAccess(sharedTo, accessType);
    }

    public String readDocument(User user, String docId) {
        Document doc = getDocument(docId);
        if (!doc.canRead(user)) {
            throw new AccessDeniedException("User does not have read access.");
        }
        return doc.getContent();
    }

    public void editDocument(User user, String docId, String newContent) {
        Document doc = getDocument(docId);
        if (!doc.canWrite(user)) {
            throw new AccessDeniedException("User does not have write access.");
        }
        doc.updateContent(newContent, user);

    }

    public List<DocumentVersion> getHistory(String docId) {
        return getDocument(docId).getVersionHistory();
    }
}