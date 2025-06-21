package com.practice.design.FileSharing.storage;


import com.practice.design.FileSharing.model.File;
import com.practice.design.FileSharing.model.User;

//Abstraction for file storage
//Interface for file storage mechanism
// Enables abstraction for pluggable storage system
public interface IStorage {
    void saveFile(User sender, User receiver , File file);
}
