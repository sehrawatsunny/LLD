package com.practice.designpractice.FileSharing.listener;

import com.practice.designpractice.FileSharing.model.User;
import com.practice.designpractice.FileSharing.model.File;

//Define the Observer Interface
//FileNotificationListener is the Observer interface.
public interface FileNotificationListener {
    void onFileReceived(User sender, User receiver, File file);
}
