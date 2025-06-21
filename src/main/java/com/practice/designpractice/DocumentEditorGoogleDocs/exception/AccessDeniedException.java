package com.practice.designpractice.DocumentEditorGoogleDocs.exception;

// --- 5. Custom Exceptions ---
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}