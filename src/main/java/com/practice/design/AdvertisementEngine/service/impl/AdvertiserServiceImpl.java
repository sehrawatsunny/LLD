package com.practice.design.AdvertisementEngine.service.impl;

import com.practice.design.AdvertisementEngine.exception.EntityNotFoundException;
import com.practice.design.AdvertisementEngine.exception.InvalidParameterException;
import com.practice.design.AdvertisementEngine.model.Advertiser;
import com.practice.design.AdvertisementEngine.service.AdvertiserService;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AdvertiserServiceImpl implements AdvertiserService {
    private final ConcurrentHashMap<String, Advertiser> advertisers = new ConcurrentHashMap<>();

    @Override
    public String addAdvertiser(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidParameterException("name", "cannot be null or empty");
        }

        String advertiserId = UUID.randomUUID().toString();
        Advertiser advertiser = new Advertiser(advertiserId, name.trim());
        advertisers.put(advertiserId, advertiser);
        return advertiserId;
    }

    @Override
    public void addBudget(String advertiserId, double budget) {
        if (budget < 0) {
            throw new InvalidParameterException("budget", "cannot be negative");
        }

        Advertiser advertiser = advertisers.get(advertiserId);
        if (advertiser == null) {
            throw new EntityNotFoundException("Advertiser", advertiserId);
        }

        advertiser.addBudget(budget);
    }

    @Override
    public Advertiser getAdvertiser(String advertiserId) {
        Advertiser advertiser = advertisers.get(advertiserId);
        if (advertiser == null) {
            throw new EntityNotFoundException("Advertiser", advertiserId);
        }
        return advertiser;
    }
}

