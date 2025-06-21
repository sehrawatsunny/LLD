package com.practice.design.FileSharing.model;

// Represents a file to be shared
// Store name and content of a file.
public class File {

    private final String fileName;
    // Simplified as string; in real app, use InputStream or file path
    private final String content;

    public File(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }
    public String getFileName() { return fileName; }
    public String getContent() { return content; }
}
