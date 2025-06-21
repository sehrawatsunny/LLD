package com.practice.design.FileSharing.listener;

import com.practice.design.FileSharing.model.User;
import com.practice.design.FileSharing.model.File;

public class UIPushNotificationListener implements FileNotificationListener {
    @Override
    public void onFileReceived(User sender, User receiver, File file) {
        System.out.println("🖥️ Push Notification to " + receiver.getName() +
                ": New file '" + file.getFileName() + "' from " + sender.getName());
    }
}