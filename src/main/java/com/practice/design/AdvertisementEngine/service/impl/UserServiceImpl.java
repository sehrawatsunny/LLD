package com.practice.design.AdvertisementEngine.service.impl;

import com.practice.design.AdvertisementEngine.exception.EntityNotFoundException;
import com.practice.design.AdvertisementEngine.exception.InvalidParameterException;
import com.practice.design.AdvertisementEngine.model.User;
import com.practice.design.AdvertisementEngine.service.UserService;

import java.util.concurrent.ConcurrentHashMap;

public class UserServiceImpl implements UserService {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    @Override
    public void addUser(String userId, int dateOfBirth, String gender) {
        validateUserParameters(userId, dateOfBirth, gender);

        User user = new User(userId, dateOfBirth, gender);
        users.put(userId, user);
    }

    @Override
    public void addAttribute(String userId, String key, String value) {
        User user = getUser(userId);
        user.addAttribute(key, value);
    }

    @Override
    public User getUser(String userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new EntityNotFoundException("User", userId);
        }
        return user;
    }

    private void validateUserParameters(String userId, int dateOfBirth, String gender) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new InvalidParameterException("userId", "cannot be null or empty");
        }
        if (dateOfBirth <= 0) {
            throw new InvalidParameterException("dateOfBirth", "must be a valid date in YYYYMMDD format");
        }
        if (gender == null || userId.trim().isEmpty()) {
            throw new InvalidParameterException("gender", "cannot be null or empty");
        }
    }
}
