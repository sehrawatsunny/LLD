package com.practice.designpractice.FileSharing.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


//Represents a user in the system
// Follow/unfollow other users
// Maintains followers/following lists
// Provides access to user identity
// Can send & Receive Files.

public class User {

    //    encapsulation and immutability
    //    private and final
    private final String userId;
    private final String name;

    private final Set<User> followers = new HashSet<>();
    private final Set<User> following = new HashSet<>();

    public User(String id, String name) {
        this.userId = id;
        this.name = name;
    }

    public String getUserId() {
        return this.userId;
    }

    //    this in Java refers to the current object
    public String getName() {
        return this.name;
    }

    public void follow(User other) {
// Need to follow other user.
        if (other == null || other == this) {
// don't want to follow if same user.
            return;
        }
// Follow other user.
        following.add(other);

// Update yourself in the list of followers of other user.
        other.followers.add(this);
    }

    public void unfollow(User other) {
        if (other == null) {
            return;
        }
// UnFollow other user.
        following.remove(other);

// Remove yourself in the list of followers of other user.
        other.followers.remove(this);
    }

    public Set<User> getFollowers() {
//        Return an unmodifiable copy or read-only view:
        return Collections.unmodifiableSet(this.followers);
//        It returns the actual reference to the internal followers set.
//        return this.followers;
    }

    public Set<User> getFollowing() {
        return Collections.unmodifiableSet(following);
    }
}
