package com.practice.designpractice.DocumentEditorGoogleDocs.model;

import java.util.*;

public class Document {
    private final String id;
    private final User owner;
    private final Map<String, AccessType> accessMap = new HashMap<>();
    private final List<DocumentVersion> versionHistory = new ArrayList<>();
    // Don't add final ,as content is editable and can't make it immutable.
    private String content;

    public Document(String id, User owner, String content) {
        this.id = id;
        this.owner = owner;
        this.content = content;
        accessMap.put(owner.getUserId(), AccessType.WRITE); // Owner has write access.
        versionHistory.add(new DocumentVersion(content, owner)); // Create the first version.
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getOwner() {
        return owner;
    }

    public List<DocumentVersion> getVersionHistory() {
        // Don't return reference to the versionHistory list.
        //  return versionHistory;
        return Collections.unmodifiableList(versionHistory);
    }

    public void addAccess(User user, AccessType accessType) {
        // Only owner of the file would be able to add access for a user.
        accessMap.put(user.getUserId(), accessType);
    }

    public boolean canRead(User user) {
        return accessMap.containsKey(user.getUserId());
    }

    public boolean canWrite(User user) {
        return accessMap.getOrDefault(user.getUserId(), AccessType.READ) == AccessType.WRITE;
    }

    public void updateContent(String newContent, User editedBy) {
        this.content = newContent; // Update newContent in the doc.
        versionHistory.add(new DocumentVersion(newContent, editedBy));
    }
}