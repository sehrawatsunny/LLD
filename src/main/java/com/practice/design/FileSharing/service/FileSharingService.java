package com.practice.design.FileSharing.service;


//Manages the file sending logic
//Delegates actual file storage to IStorage
//Applies Single Responsibility Principle


// For notification , extending the class :
//  FileSharingService is the Subject/Publisher.
import com.practice.design.FileSharing.listener.FileNotificationListener;
import com.practice.design.FileSharing.model.File;
import com.practice.design.FileSharing.model.User;
import com.practice.design.FileSharing.storage.IStorage;

import java.util.ArrayList;
import java.util.List;

public class FileSharingService {

//    | Approach                        | When to Use                                                                        |
//    | ------------------------------- | ---------------------------------------------------------------------------------- |
//    | **Instance field (non-static)** | When each service should have independent storage (e.g., for testing, sharding)    |
//    | **Static field**                | When all services must share the same global state (e.g., singleton storage)       |
//    | **Shared instance passed in**   | When you want shared state but still need flexibility (✅ most flexible + testable) |

    private final IStorage storage;

    public FileSharingService(IStorage storage){
        this.storage = storage;
    }

    // To Support Listeners and sending notification through observer pattern.
    // Multiple notifications needs to be sent , such as email and UI notification
//    This listeners list stores all the subscribed observers (i.e., notification channels like Email, UI, SMS, etc.).
    private final List<FileNotificationListener> listeners = new ArrayList<>();

    public void addListener(FileNotificationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(FileNotificationListener listener) {
        listeners.remove(listener);
    }

    public void sendFile(User sender, User receiver, File file) {
        storage.saveFile(sender,receiver, file);
        // Notify all the listeners
        notifyListeners(sender, receiver, file);
    }

    private void notifyListeners(User sender, User receiver, File file) {
        for (FileNotificationListener listener : listeners) {
            listener.onFileReceived(sender, receiver, file);
        }
    }
}

