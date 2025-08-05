package com.practice.design.AdvertisementEngine.service;

import com.practice.design.AdvertisementEngine.model.Advertiser;

public interface AdvertiserService {
    String addAdvertiser(String name);

    void addBudget(String advertiserId, double budget);

    Advertiser getAdvertiser(String advertiserId);
}
