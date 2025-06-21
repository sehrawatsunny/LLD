package com.practice.designpractice.FileSharing.storage;

import com.practice.designpractice.FileSharing.model.File;
import com.practice.designpractice.FileSharing.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// In-memory file storage.
// Simple implementation of IStorage Abstract and pluggable interface
public class InMemoryStorage implements IStorage {

    // Best for storing per-user or per-instance data.
    // As it will create a new Map for every instance of the class.
    private final Map<String, List<File>> receivedFiles = new HashMap<>();


    @Override
    public void saveFile(User sender, User receiver, File file) {
        // Save the file to the map with id as receiver id.
        //    receivedFiles.putIfAbsent(receiver.getUserId(), new ArrayList<>());
        //    receivedFiles.get(receiver.getUserId()).add(file);
        String receiverId = receiver.getUserId();
        receivedFiles.computeIfAbsent(receiverId, k -> new ArrayList<>()).add(file);

        System.out.println("File '" + file.getFileName() + "' sent from " + sender.getName() +
                " to " + receiver.getName());
    }

    public List<File> getFilesForUser(User user){
        return receivedFiles.getOrDefault(user.getUserId(),List.of());
    }
}