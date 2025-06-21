package com.practice.designpractice.CollaborativeDocumentEditor.core;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

// EditOperation.java
public abstract class EditOperation {
    protected final String userId;
    protected final LocalDateTime timestamp;
    @Getter
    protected final String versionId;

    public EditOperation(String userId) {
        this.userId = userId;
        this.timestamp = LocalDateTime.now();
        this.versionId = UUID.randomUUID().toString();
    }

    public abstract String apply(String content);

}
