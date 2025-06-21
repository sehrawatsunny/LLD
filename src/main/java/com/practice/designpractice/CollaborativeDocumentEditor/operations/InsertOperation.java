package com.practice.designpractice.CollaborativeDocumentEditor.operations;

import com.practice.designpractice.CollaborativeDocumentEditor.core.EditOperation;

// InsertOperation.java
public class InsertOperation extends EditOperation {
    private final int position;
    private final String text;

    public InsertOperation(String userId, int position, String text) {
        super(userId);
        this.position = position;
        this.text = text;
    }

    @Override
    // Content is document whole content.
    public String apply(String content) {
        if (position < 0 || position > content.length())
            return content;
        return content.substring(0, position) + text + content.substring(position);
    }
}