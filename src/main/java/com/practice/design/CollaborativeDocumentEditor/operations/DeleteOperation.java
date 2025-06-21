package com.practice.design.CollaborativeDocumentEditor.operations;

import com.practice.design.CollaborativeDocumentEditor.core.EditOperation;

public class DeleteOperation extends EditOperation {
    private final int position;
    private final int length;

    public DeleteOperation(String userId, int position, int length) {
        super(userId);
        this.position = position;
        this.length = length;
    }


    /**
     * @param content
     * @return
     */
    @Override
    public String apply(String content) {
        if (position < 0 || position + length > content.length())
            return content;
        return content.substring(0, position) + content.substring(position + length);
    }
}