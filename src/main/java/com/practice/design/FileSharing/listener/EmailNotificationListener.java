package com.practice.design.FileSharing.listener;


//EmailNotificationListener, UIPushNotificationListener, etc., are concrete observers.

import com.practice.design.FileSharing.model.User;
import com.practice.design.FileSharing.model.File;

public class EmailNotificationListener implements FileNotificationListener {

    @Override
    public void onFileReceived(User sender , User receiver, File file){
                System.out.println("📧 Email to " + receiver.getName() +
                ": You've received a file '" + file.getFileName() +
                "' from " + sender.getName());
    }
}