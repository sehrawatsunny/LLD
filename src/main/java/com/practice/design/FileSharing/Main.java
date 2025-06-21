package com.practice.design.FileSharing;


import com.practice.design.FileSharing.model.File;
import com.practice.design.FileSharing.model.User;
import com.practice.design.FileSharing.service.FileSharingService;
import com.practice.design.FileSharing.service.FollowerService;
import com.practice.design.FileSharing.storage.IStorage;
import com.practice.design.FileSharing.storage.InMemoryStorage;

//Requirements :
//1. Send files between users
//2. Allow users to follow/unfollow other users
//3. Show follower/following list
public class Main {
    public static void main(String[] args){
        User alice = new User("u1","Alice");
        User bob = new User("u2", "Bob");

        User charlie = new User("u3","Charlie");

        // Followers Logic.
        // ALlow users to follow / unfollow other users.
        alice.follow(bob);
        charlie.follow(bob);

        //Show followers/unfollowers
        FollowerService followerService = new FollowerService();
        followerService.showFollowers(bob);
        followerService.showFollowing(alice);

        // File sharing logic.
        // Create a storage object.
        IStorage storage = new InMemoryStorage();

        FileSharingService fileSharingService = new FileSharingService(storage);

        File file = new File("photo.jpg","Image content");
        fileSharingService.sendFile(alice,bob,file);
    }
}

