package com.practice.design.AdvertisementEngine.model;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class User {
    private final String userId;
    private final int dateOfBirth;
    private final String gender;
    private final Map<String, String> attributes;
    private final Deque<String> recentAdvertisements; // For tracking last 10 ads

    public User(String userId, int dateOfBirth, String gender) {
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.attributes = new HashMap<>();
        this.recentAdvertisements = new ConcurrentLinkedDeque<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getGender() {
        return gender;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public Map<String, String> getAttributes() {
        return new HashMap<>(attributes);
    }

    public void addAttribute(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Attribute key and value cannot be null");
        }
        attributes.put(key, value);
    }

    public synchronized boolean hasSeenRecentAd(String adId) {
        return recentAdvertisements.contains(adId);
    }

    public synchronized void addViewedAd(String adId) {
        recentAdvertisements.addLast(adId);
        if (recentAdvertisements.size() > 10) {
            recentAdvertisements.removeFirst();
        }
    }

    public int getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - (dateOfBirth / 10000); // ex dob = 19991004
    }
}
