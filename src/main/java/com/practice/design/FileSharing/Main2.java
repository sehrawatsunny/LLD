package com.practice.design.FileSharing;

import com.practice.design.FileSharing.listener.EmailNotificationListener;
import com.practice.design.FileSharing.listener.UIPushNotificationListener;
import com.practice.design.FileSharing.model.File;
import com.practice.design.FileSharing.model.User;
import com.practice.design.FileSharing.service.FileSharingService;
import com.practice.design.FileSharing.storage.IStorage;
import com.practice.design.FileSharing.storage.InMemoryStorage;

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
