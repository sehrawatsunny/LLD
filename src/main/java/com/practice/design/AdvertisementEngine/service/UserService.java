package com.practice.design.AdvertisementEngine.service;

import com.practice.design.AdvertisementEngine.model.User;

public interface UserService {
    void addUser(String userId, int dateOfBirth, String gender);
    void addAttribute(String userId, String key, String value);
    User getUser(String userId);
}