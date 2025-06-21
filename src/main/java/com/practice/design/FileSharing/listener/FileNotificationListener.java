package com.practice.design.FileSharing.listener;

import com.practice.design.FileSharing.model.User;
import com.practice.design.FileSharing.model.File;

//Define the Observer Interface
//FileNotificationListener is the Observer interface.
public interface FileNotificationListener {
    void onFileReceived(User sender, User receiver, File file);
}
