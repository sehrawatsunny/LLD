package com.practice.designpractice.FileSharing;

import com.practice.designpractice.FileSharing.listener.EmailNotificationListener;
import com.practice.designpractice.FileSharing.listener.UIPushNotificationListener;
import com.practice.designpractice.FileSharing.model.File;
import com.practice.designpractice.FileSharing.model.User;
import com.practice.designpractice.FileSharing.service.FileSharingService;
import com.practice.designpractice.FileSharing.storage.IStorage;
import com.practice.designpractice.FileSharing.storage.InMemoryStorage;

public class Main2 {
    public static void main(String[] args) {
        IStorage storage = new InMemoryStorage(); // your own implementation
        FileSharingService service = new FileSharingService(storage);

        // Add notification channels
        service.addListener(new EmailNotificationListener());
        service.addListener(new UIPushNotificationListener());

        // Dummy users and file
        User alice = new User("u1", "Alice");
        User bob = new User("u2", "Bob");
        File file = new File("report.pdf","Pdf content of file");

        service.sendFile(alice, bob, file);
    }
}
