package com.practice.designpractice.FileSharing.service;

import com.practice.designpractice.FileSharing.model.User;

//Handles displaying followers/following
// Keeps follow logic decoupled from UI and user data
public class FollowerService {
    public void showFollowers(User user){
        System.out.println("Followers of "+user.getName()+":");

        for(User follower : user.getFollowers()){
            System.out.println("-" + follower.getName());
        }
    }

    public void showFollowing(User user){
        System.out.println(user.getName()+" is following : ");
        for(User followee : user.getFollowing()){
            System.out.println("-" + followee.getName());
        }
    }
}