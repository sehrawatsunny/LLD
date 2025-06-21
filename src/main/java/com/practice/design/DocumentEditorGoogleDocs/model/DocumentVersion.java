package com.practice.design.DocumentEditorGoogleDocs.model;

import java.time.LocalDateTime;

public class DocumentVersion {
    private final String content;
    private final User editedBy;
    private final LocalDateTime timestamp;

    public DocumentVersion(String c , User u){
        this.content = c;
        this.editedBy = u ;
        // Set current time as the timestamp.
        this.timestamp = LocalDateTime.now();;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getEditedBy() {
        return editedBy;
    }
}
